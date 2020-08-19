package com.conmuti.unittests.mutator.coreclasses;

import com.conmuti.conmuti.datalogger.DataLog;
import com.conmuti.conmuti.main.UserData;
import com.conmuti.conmuti.mutator.coreclasses.MutationWorker;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class MutationWorkerTest {

    @Test
    public void classFilter(){
        FilenameFilter classFilter = MutationWorker.classFilter();
        File file = new File("aclassfile.class");
        assertTrue(classFilter.accept(file,file.getName()));
        file = new File("notaclassfile.txt");
        assertFalse(classFilter.accept(file,file.getName()));
    }


    @Test
    public void compileMutant() throws IOException {
        //test compilation failure
        File testFile = new File("TestFile.java");
        testFile.createNewFile();
        PrintStream writeLog = new PrintStream(new FileOutputStream("TestFile.java",true));
        writeLog.println("not a compilable java file");
        writeLog.close();
        String javaFilePath = System.getProperty("user.dir")+"\\TestFile.java";
        UserData ud = new UserData(javaFilePath,"","");
        int compileSuccess = MutationWorker.compileMutant(ud);
        assertNotEquals(0,compileSuccess);
        testFile.delete();

        //test compilation success
        testFile = new File("TestFile.java");
        testFile.createNewFile();
        writeLog = new PrintStream(new FileOutputStream("TestFile.java",true));
        writeLog.println("public class TestFile {\n" +
                "}");
        writeLog.close();
        javaFilePath = System.getProperty("user.dir")+"\\TestFile.java";
        ud = new UserData(javaFilePath,"","");
        compileSuccess = MutationWorker.compileMutant(ud);
        assertEquals(0,compileSuccess);
        testFile.delete();

        //delete compiled file
        File compiledFile = new File("TestFile.class");
        compiledFile.delete();
    }

    @Test
    public void getNoOfMutationOpportunities() {
        RSTKCounter rstk = new RSTKCounter();
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public static synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        int count = MutationWorker.getNoOfMutationOpportunities(cu,rstk);
        assertEquals(1,count);
    }

    @Test
    public void getUnmodifiedCU() throws IOException {
        File savedCode = new File("SavedCode.java");
        savedCode.delete();
        savedCode.createNewFile();
        PrintStream writeLog = new PrintStream(new FileOutputStream("SavedCode.java",true));
        writeLog.println("package com.example.example;\n" +
        "\n" +
                "public class TestClass {\n" +
                "    public static synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        writeLog.close();
        CompilationUnit cu = MutationWorker.getUnmodifiedCU();
        assertTrue(cu != null);
        assertTrue(cu instanceof CompilationUnit);
        savedCode.delete();
        File mutantJava = new File("Mutant.java");
        mutantJava.delete();
    }

    @Test
    public void javaFilter(){
        FilenameFilter javaFilter = MutationWorker.javaFilter();
        File file = new File("ajavafile.java");
        assertTrue(javaFilter.accept(file,file.getName()));
        file = new File("notajavafile.txt");
        assertFalse(javaFilter.accept(file,file.getName()));
    }


    @Test
    public void logResult() throws IOException {
        DataLog log = new DataLog(0,"","","",0);
        MutationWorker.logResult(log);

        BufferedReader input = new BufferedReader(new FileReader("log.txt"));
        String last, line;
        last = null;

        while ((line = input.readLine()) != null) {
            last = line;
        }
        assertEquals("[O_O],LIVE mutant,TYPE: ,FILE: ,METHOD: ,LINE: 0",last);
    }

    @Test
    public void moveClassFiles() throws IOException {
        String folder1 = System.getProperty("user.dir")+"\\Folder1";
        String folder2 = System.getProperty("user.dir")+"\\Folder2";
        new File(folder1).mkdir();
        new File(folder2).mkdir();
        String classFile = folder1+"\\classfile.class";
        new File(classFile).createNewFile();
        UserData userData = new UserData(folder1+"\\classfile.java",folder2+"\\classfile.class","");
        MutationWorker.moveClassFiles(userData);
        File testOriginal = new File(folder1+"\\classfile.class");
        assertFalse(testOriginal.exists());
        File testMoved = new File(folder2+"\\classfile.class");
        assertTrue(testMoved.exists());
        testMoved.delete();
        File fileFolder1 = new File(folder1);
        fileFolder1.delete();
        File fileFolder2 = new File(folder2);
        fileFolder2.delete();
    }

    @Test
    public void moveMutantToSourceDirectory() throws IOException {
        String folder1 = System.getProperty("user.dir")+"\\Folder1";
        String mutantFile = System.getProperty("user.dir")+"\\mutant.java";
        new File(mutantFile).createNewFile();
        new File(folder1).mkdir();
        UserData userData = new UserData(folder1+"\\test.java","","");
        MutationWorker.moveMutantToSourceDirectory(userData);
        File newFile = new File(folder1+"\\test.java");
        assertTrue(newFile.exists());

        //cleanup
        File deleteFileInFolder1 = new File(folder1+"\\test.java");
        deleteFileInFolder1.delete();
        File deleteFolder1 = new File(folder1);
        deleteFolder1.delete();
        File deleteMutantJava = new File(mutantFile);
        deleteMutantJava.delete();
    }

    @Test
    public void run() {
        //tested as part of user evaluation
    }

    @Test
    public void saveCU() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public static synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        String mutantFile = System.getProperty("user.dir")+"\\Mutant.java";
        File fileMutant = new File(mutantFile);
        fileMutant.delete();
        MutationWorker.saveCU(cu);
        assertTrue(fileMutant.exists());
        fileMutant.delete();
        assertFalse(fileMutant.exists());
    }
}