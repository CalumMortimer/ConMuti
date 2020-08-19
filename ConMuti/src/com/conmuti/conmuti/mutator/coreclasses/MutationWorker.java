package com.conmuti.conmuti.mutator.coreclasses;

import com.conmuti.conmuti.datalogger.DataLog;
import com.conmuti.conmuti.main.UserData;
import com.conmuti.conmuti.testrunner.DirectoryTestRunner;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * An abstract class with static methods which work through mutations
 */
public abstract class MutationWorker {

    /**
     * A method to return a file name filter which filters out class files
     *
     * @return - a class file name filter
     */
    public static FilenameFilter classFilter(){
        FilenameFilter classFilter = new FilenameFilter(){
            public boolean accept(File dir,String name){
                if (name.endsWith(".class")){
                    return true;
                }else{
                    return false;
                }
            }
        };
        return classFilter;
    }

    /**
     * A method which compiles the java source code files located within the user's java file directory
     *
     * @param userData - An object representing the user's file system
     * @return - 0 if compilation successful, non-zero otherwise
     */
    public static int compileMutant(UserData userData) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        File javaDirectory = new File(userData.getJavaDirectory());
        FilenameFilter javaFilter = javaFilter();
        String javaFiles[] = javaDirectory.list(javaFilter);
        String[] fullJavaFileNames = new String[javaFiles.length];
        for (int i = 0; i < javaFiles.length; i++) {
            fullJavaFileNames[i] = userData.getJavaDirectory() + "\\" + javaFiles[i];
        }

        int result = compiler.run(null, null, null, fullJavaFileNames);

        return result;
    }


    /**
     * A method to obtain the number of mutation opportunities of a given mutation type
     * within a compilation unit
     *
     * @param cu - the compilation unit
     * @param type - the type of mutation (an object extending the MutationCounter class)
     * @return - the number of mutation opportunities
     */
    public static int getNoOfMutationOpportunities(CompilationUnit cu,MutationCounter type){
        type.visit(cu,null);
        return type.getMutantCount();
    }

    /**
     * A method which gets the CompilationUnit of the source code stored in the local "SavedCode.java" file
     *
     * @return - the CompilationUnit Abstract Syntax Tree of the unmodified "SavedCode.java" file
     */
    public static CompilationUnit getUnmodifiedCU(){
        CompilationUnit cu = null;
        try {
            cu = JavaParser.parse(new File("SavedCode.java"));
        }
        catch(FileNotFoundException e){
            System.out.println("SavedCode.java not found in com.conmuti.conmuti.mutator.MutationOperator.getCompilationUnit");
        }

        return cu;
    }

    /**
     * A method to return a file name filter which filters out java files
     *
     * @return - a java file name filter
     */
    public static FilenameFilter javaFilter(){
        FilenameFilter javaFilter = new FilenameFilter(){
            public boolean accept(File dir,String name){
                if (name.endsWith(".java")){
                    return true;
                }else{
                    return false;
                }
            }
        };
        return javaFilter;
    }


    /**
     * Calls the DataLog.logResult method on a DataLog object (this removes the requirement for the "run" method
     * to handle an exception)
     *
     * @param log - the DataLog object to be logged in the data log
     */
    public static void logResult(DataLog log){
        try{
            log.logResult();
        }
        catch(IOException e){
            System.out.println("IO Exception within com.conmuti.conmuti.mutator.coreclasses.MutationWorker.logResult");
        }
    }

    /**
     * A method to move compiled class files from the java directory to the class directory
     *
     * @param userData - a representation of the user's file system
     */
    public static
    void moveClassFiles(UserData userData) {
        File javaDirectory = new File(userData.getJavaDirectory());
        FilenameFilter classFilter = classFilter();
        String classFiles[] = javaDirectory.list(classFilter);
        String[] fullClassFileNames = new String[classFiles.length];
        for (int i=0;i<classFiles.length;i++){
            fullClassFileNames[i] = userData.getJavaDirectory()+"\\"+classFiles[i];
        }

        try {
            for (int i = 0; i < classFiles.length; i++) {
                Files.move(Paths.get(fullClassFileNames[i]), Paths.get(userData.getClassDirectory()+"\\"+classFiles[i]), REPLACE_EXISTING);
            }
        }catch(IOException e){
            System.out.println("IO exception in com.conmuti.conmuti.mutator.coreclasses.MutationWorker.moveClassFiles()");
        }
    }

    /**
     * A method which moves the mutant source code, stored as "Mutant.java" in the ConMuti filepath,
     * to replace the original source code file at the user source code root
     *
     * @param userData - An object representing the user's file system
     */
    public static void moveMutantToSourceDirectory(UserData userData){
        try{
            Files.copy(Paths.get("Mutant.java"), Paths.get(userData.getJavaFileName()), REPLACE_EXISTING);
        }
        catch(IOException e){
            System.out.println("IO Exception within com.conmuti.conmuti.mutator.MutationOperator.moveMutantToSourceDirectory");
        }
    }

    /**
     * A method which applies, tests, and logs mutations
     *
     * @param userData - an object representing the user's file system
     * @param directoryTestRunner - an object providing unit test capability
     * @param counter - any MutationCounter object
     * @param modifier - any MutationModifier object
     * @param type - a string representing the mutation type
     */
    public static void run(UserData userData, DirectoryTestRunner directoryTestRunner, MutationCounter counter,
                           MutationModifier modifier, String type){
        CompilationUnit cu = getUnmodifiedCU();

        int numberOfMutations = getNoOfMutationOpportunities(cu,counter);

        for (int i=0;i<numberOfMutations;i++){
            cu = getUnmodifiedCU();
            modifier.setMutationsVisited(0);
            modifier.setMutationNumber(i);
            modifier.visit(cu,null);
            saveCU(cu);
            moveMutantToSourceDirectory(userData);
            int compileSuccess = compileMutant(userData);
            if (compileSuccess == 0) {
                moveClassFiles(userData);
                int mutantKilled = directoryTestRunner.runAll();
                DataLog log = new DataLog(mutantKilled, type, userData.getShortJavaFileName(),modifier.getMethodName(),
                        modifier.getMutantLineReference());
                logResult(log);
            }
        }
    }

    /**
     * A method which saves a CompilationUnit - the object representing the Abstract Syntax Tree of the source code,
     * and stores it in String form as "Mutant.java" in the local ConMuti folder
     *
     * @param cu - A CompilationUnit: the Abstract Syntax Tree of a computer program.
     */
    public static void saveCU(CompilationUnit cu){
        File file = new File("Mutant.java");
        if (file.exists()){
            boolean deleteSuccess = file.delete();
        }
        try{
            boolean createSuccess = file.createNewFile();
        }
        catch(IOException iOE){
            System.out.println("I/O exception in com.conmuti.conmuti.mutator.coreclasses.MutationWorker.saveCU");
        }
        PrintStream codeCopyWriter = null;
        try {
            codeCopyWriter = new PrintStream(file);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found exception in com.conmuti.conmuti.mutator.coreclasses.MutationWorker.saveCU");
        }
        assert codeCopyWriter != null;
        codeCopyWriter.println(cu.toString());
        codeCopyWriter.close();
    }
}
