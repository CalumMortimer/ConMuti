package com.conmuti.unittests.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MSPSystemOutModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Object o = new Object();\r\n" +
                "        synchronized (o) {\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n");
        MSPSystemOutModifier msp = new MSPSystemOutModifier();
        msp.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Object o = new Object();\r\n" +
                "        synchronized (System.out) {\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}