package com.conmuti.unittests.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutModifier;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPThisModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MSPThisModifierTest {

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
        MSPThisModifier msp = new MSPThisModifier();
        msp.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Object o = new Object();\r\n" +
                "        synchronized (this) {\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}