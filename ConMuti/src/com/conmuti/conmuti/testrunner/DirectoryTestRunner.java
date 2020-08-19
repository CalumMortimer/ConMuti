package com.conmuti.conmuti.testrunner;

import com.conmuti.conmuti.main.UserData;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Runs unit tests found within the user's unit test directory
 * against objects found in the user's class directory
 */
public class DirectoryTestRunner {
    private UserData userData;
    private String testPackageName;
    private String classPackageName;
    private ArrayList<String> testClassNames;
    private ArrayList<String> classNames;
    private String directoryToClassPackage;
    private String directoryToTestPackage;

    public DirectoryTestRunner(UserData userData) {
        this.userData = userData;
        testPackageName = "";
        classPackageName = "";
        this.testClassNames = new ArrayList<String>();
        this.classNames = new ArrayList<String>();
        directoryToClassPackage = userData.getClassDirectory();
        directoryToTestPackage = userData.getUnitTestDirectory();
        extractClassNames(userData.getClassDirectory(), classNames);
        extractClassNames(userData.getUnitTestDirectory(), testClassNames);
        initialize();
    }

    /**
     * A method which extracts class names from a directory and stores them as
     * an array list
     *
     * @param directory  - the directory in which to look for classes
     * @param classNames - the arraylist which shall contain the class names
     * @return
     */
    public int extractClassNames(String directory, ArrayList<String> classNames) {
        File file = new File(directory);
        File[] classes = file.listFiles();
        try {
            for (int i = 0; i < classes.length; i++) {
                String filename = classes[i].getName();
                if (filename.contains(".class") && !filename.contains("$")) {
                    filename = filename.substring(0, filename.lastIndexOf("."));
                    classNames.add(filename.substring(filename.lastIndexOf("\\\\") + 1));
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception within com.conmuti.testrunner.DirectoryTestRunner.extractClassNames -" +
                    " check that the file directory contains classes");
            return -1;
        }
        return 0;
    }

    /**
     * A method which attempts to locate the package name of the implementing classes
     *
     * @return - 0 if successful, non-zero otherwise
     */
    public int findClassPackage() {
        //try to establish the package name of the target classes
        boolean checkClassDirectoryLevel = false;
        for (int i = 0; i < 5; i++) {
            checkClassDirectoryLevel = checkClassDirectoryLevel();
            if (checkClassDirectoryLevel) {
                break;
            }
        }
        if (!checkClassDirectoryLevel()) {
            System.out.println("Fatal error within com.conmuti.testrunner.DirectoryRunner.intialize - could not establish " +
                    "the package structure within the target unit test folder. Try and shorten the package name of the target" +
                    "test directory.");
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * A method which attempts to locate the package name of the tests
     *
     * @return - 0 if successful, non-zero otherwise
     */
    public int findTestPackage() {
        boolean checkTestDirectoryLevel = false;
        for (int i = 0; i < 5; i++) {
            checkTestDirectoryLevel = checkTestDirectoryLevel();
            if (checkTestDirectoryLevel) {
                break;
            }
        }
        if (!checkTestDirectoryLevel()) {
            System.out.println("Fatal error within com.conmuti.testrunner.DirectoryRunner.intialize - could not establish " +
                    "the package structure within the target unit test folder. Try and shorten the package name of the target" +
                    "test directory.");
            return -1;
        } else {
            return 0;


        }
    }

    /**
     * A method to initialize the packages and classes
     */
    public void initialize() {
        findClassPackage();
        findTestPackage();
    }


    /**
     * A method which checks if a class file runs within the class directory,
     * If it doesn't then the directory is shortened and the package name is extended
     * until the directory name is shortened to the start of the package and the package
     * name is lengthened to the class' package name
     *
     * @return - true if successful, false if unsuccessful
     */
    public boolean checkClassDirectoryLevel() {
        boolean entrySuccess = true;
        File file = new File(directoryToClassPackage);

        Class myClass = null;

        //try to load the class under the assumed package structure
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader c1 = new URLClassLoader(urls);

            if (this.classPackageName == "") {
                myClass = c1.loadClass(this.classNames.get(0));
            } else {
                myClass = c1.loadClass(this.classPackageName + "." + this.classNames.get(0));
            }
        }
        //increase the length of the package name, decrease the length of the directory
        catch (ClassNotFoundException | NoClassDefFoundError ex) {
            entrySuccess = false;
            //shorten the class directory by one level and lengthen the package name
            String[] split = this.directoryToClassPackage.split("\\\\");
            if (this.classPackageName.equals("")) {
                this.classPackageName = split[split.length - 1];
            } else {

                this.classPackageName = split[split.length - 1] + "." + this.classPackageName;
            }

            this.directoryToClassPackage = this.directoryToClassPackage.substring(0, this.directoryToClassPackage.lastIndexOf("\\"));
        } catch (MalformedURLException mlf) {
            System.out.println("Malformed URL in com.conmuti.testrunner.DirectoryRunner.checkDirectoryLevel");
        }
        return entrySuccess;
    }

    /**
     * A method which checks if a test file runs within the test directory,
     * If it doesn't then the directory is shortened and the package name is extended
     * until the directory name is shortened to the start of the package and the package
     * name is lengthened to the class' package name
     *
     * @return - true if successful, false if unsuccessful
     */
    public boolean checkTestDirectoryLevel() {
        boolean entrySuccess = true;
        File file = new File(directoryToTestPackage);

        Class myClass = null;

        //try to load the class under the assumed package structure
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader c1 = new URLClassLoader(urls);

            if (this.testPackageName == "") {
                myClass = c1.loadClass(this.testClassNames.get(0));
            } else {
                myClass = c1.loadClass(this.testPackageName + "." + this.testClassNames.get(0));
            }
        }
        //increase the length of the package name, decrease the length of the directory
        catch (ClassNotFoundException | NoClassDefFoundError ex) {
            entrySuccess = false;
            //shorten the class directory by one level and lengthen the package name
            String[] split = this.directoryToTestPackage.split("\\\\");
            if (this.testPackageName.equals("")) {
                this.testPackageName = split[split.length - 1];
            } else {

                this.testPackageName = split[split.length - 1] + "." + this.testPackageName;
            }

            this.directoryToTestPackage = this.directoryToTestPackage.substring(0, this.directoryToTestPackage.lastIndexOf("\\"));
        } catch (MalformedURLException mlf) {
            System.out.println("Malformed URL in com.conmuti.testrunner.DirectoryRunner.checkDirectoryLevel");
        }
        return entrySuccess;
    }

    /**
     * A method to run all unit tests against the classes loaded from the class package which includes one mutant class
     *
     * @return - 0 if the mutant is not killed, 1 if the mutant is killed
     */
    public int runAll() {
        int mutantKilled = 0;
        Result testResult;

        JUnitCore junit = new JUnitCore();

        File myClasses = new File(this.directoryToClassPackage);
        File myTests = new File(this.directoryToTestPackage);

        URL classURL = null;
        URL testURL = null;
        try {
            classURL = myClasses.toURI().toURL();
            testURL = myTests.toURI().toURL();
        }
        catch(MalformedURLException e){
            System.out.println("malformed url exception in com.conmuti.conmuti.testrunner.DirectoryTestRunner.runAll");
            return -1;
        }

        URL[] urls = {classURL,testURL};

        URLClassLoader c1 = new URLClassLoader(urls);

        for (int i = 0 ;i<this.classNames.size();i++){
            try {
                if (this.classPackageName == ""){
                    c1.loadClass(classNames.get(i));
                }
                else{
                    c1.loadClass(classPackageName + "." + classNames.get(i));
                }
            }catch (ClassNotFoundException e){
                System.out.println("class not found exception in com.conmuti.conmuti.testrunner.DirectoryTestRunner.runAll");
                return -1;
            }
        }

        for (int i=0;i<this.testClassNames.size();i++){
            try{
                if (this.testPackageName == ""){
                    testResult = junit.run(c1.loadClass(this.testClassNames.get(i)));
                }
                else{
                    testResult = junit.run(c1.loadClass(this.testPackageName+"."+this.testClassNames.get(i)));
                }
                if (mutantKilled==0){
                    if (testResult.getFailureCount()>0){
                        mutantKilled = 1;
                    }
                }
            }
            catch(ClassNotFoundException e){
                System.out.println("Class not found exception in com.conmuti.testrunner.DirectoryRunner.runAll");
                return -1;
            }
        }
        return mutantKilled;
    }
}


