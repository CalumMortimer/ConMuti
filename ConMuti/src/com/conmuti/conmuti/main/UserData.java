package com.conmuti.conmuti.main;

import java.io.File;

/**
 * A class to store the various paths on the user filesystem used by the program
 */
public class UserData {
    private final String javaFileName;
    private final String classFileName;
    private final String unitTestDirectory;
    private final String javaDirectory;
    private final String classDirectory;
    private final String shortJavaFileName;
    private final String shortClassFileName;

    /**
     *
     * @param javaFileName - the full file path to the java file, e.g. C://MyProject/src/MyFile.java
     * @param classFileName - the full file path to the compiled class file, e.g. C://MyProject/out/MyFile.class
     * @param unitTestDirectory - the full file path to the compiled unit testing directory, e.g. C://MyProject/out/test
     */
    public UserData(String javaFileName,String classFileName,String unitTestDirectory){
        this.javaFileName = javaFileName;
        this.classFileName = classFileName;
        this.unitTestDirectory = unitTestDirectory;
        this.javaDirectory = directoryName(javaFileName);
        this.classDirectory = directoryName(classFileName);
        this.shortJavaFileName = shortName(javaFileName);
        this.shortClassFileName = shortName(classFileName);
    }

    public String getJavaFileName() {
        return javaFileName;
    }

    public String getClassFileName() {
        return classFileName;
    }

    public String getUnitTestDirectory() {
        return unitTestDirectory;
    }

    public String getShortJavaFileName() {
        return shortJavaFileName;
    }

    public String getClassDirectory() {
        return classDirectory;
    }

    public String getJavaDirectory() {
        return javaDirectory;
    }

    public String getShortClassFileName() {
        return shortClassFileName;
    }

    /***
     * A method to return the directory of a provided file name
     * e.g. C://MyProject/src/MyFile.java becomes C://MyProject/src/
     *
     * @param fileName - the file path
     * @return - the directory path
     */
    private static String directoryName(String fileName){
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        return file.getParent();
    }

    /**
     * A method to convert the full java file path to the java file name
     * e.g. C://MyProject/src/MyFile.java becomes MyFile.java
     *
     * @param longName - the full file path
     * @return - the shortened file path
     */
    private static String shortName(String longName){
        String[] splitFileName = longName.split("\\\\");
        return splitFileName[splitFileName.length-1];
    }
}
