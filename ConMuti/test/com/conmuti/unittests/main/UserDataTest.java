package com.conmuti.unittests.main;

import com.conmuti.conmuti.main.UserData;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataTest {

    @Test
    public void getJavaFileName() {
        String javaFile = "C://MyProject/src/MyFile.java";
        String classFile = "C://MyProject/out/MyFile.class";
        String tests = "C://MyProject/out/test";
        UserData data = new UserData(javaFile,classFile,tests);
        assertEquals("C://MyProject/src/MyFile.java",data.getJavaFileName());
    }

    @Test
    public void getClassFileName() {
        String javaFile = "C://MyProject/src/MyFile.java";
        String classFile = "C://MyProject/out/MyFile.class";
        String tests = "C://MyProject/out/test";
        UserData data = new UserData(javaFile,classFile,tests);
        assertEquals("C://MyProject/out/MyFile.class",data.getClassFileName());
    }

    @Test
    public void getUnitTestDirectory() {
        String javaFile = "C://MyProject/src/MyFile.java";
        String classFile = "C://MyProject/out/MyFile.class";
        String tests = "C://MyProject/out/test";
        UserData data = new UserData(javaFile,classFile,tests);
        assertEquals("C://MyProject/out/test",data.getUnitTestDirectory());
    }

    //deprecated tests
    /*@Test
    public void getShortJavaFileName() {
        String javaFile = "C://MyProject/src/MyFile.java";
        String classFile = "C://MyProject/out/MyFile.class";
        String tests = "C://MyProject/out/test";
        UserData data = new UserData(javaFile,classFile,tests);
        assertEquals("MyFile.java",data.getShortJavaFileName());
    }*/

    //deprecated tests
   /* @Test
    public void getTempClassFileName() {
        String javaFile = "C://MyProject/src/MyFile.java";
        String classFile = "C://MyProject/out/MyFile.class";
        String tests = "C://MyProject/out/test";
        UserData data = new UserData(javaFile,classFile,tests);
        assertEquals("C://MyProject/src/MyFile.class",data.getTempClassFileName());
    }*/
}