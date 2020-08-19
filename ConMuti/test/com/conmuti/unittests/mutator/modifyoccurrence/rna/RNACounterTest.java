package com.conmuti.unittests.mutator.modifyoccurrence.rna;

import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rna.RNACounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rna.RNAModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RNACounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n");
        RNAModifier rna = new RNAModifier();
        rna.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.notify();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}