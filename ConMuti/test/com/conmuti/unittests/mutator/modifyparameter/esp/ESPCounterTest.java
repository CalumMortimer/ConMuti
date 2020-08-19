package com.conmuti.unittests.mutator.modifyparameter.esp;

import com.conmuti.conmuti.mutator.modifyparameter.esp.ESPCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ESPCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        Object o1 = new Object();\n" +
                "        Object o2 = new Object();\n" +
                "        synchronized (o1) {\n" +
                "            synchronized (o2) {\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        ESPCounter esp = new ESPCounter();
        esp.visit(cu,null);
        assertEquals(1,esp.getMutantCount());
    }
}