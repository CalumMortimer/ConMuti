package com.conmuti.unittests.mutator.modifyparameter.esp;

import com.conmuti.conmuti.mutator.modifyparameter.esp.ESPCounter;
import com.conmuti.conmuti.mutator.modifyparameter.esp.ESPModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ESPModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Object o1 = new Object();\r\n" +
                "        Object o2 = new Object();\r\n" +
                "        synchronized (o1) {\r\n" +
                "            synchronized (o2) {\r\n" +
                "            }\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n");
        ESPModifier esp = new ESPModifier();
        esp.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Object o1 = new Object();\r\n" +
                "        Object o2 = new Object();\r\n" +
                "        synchronized (o2) {\r\n" +
                "            synchronized (o1) {\r\n" +
                "            }\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}