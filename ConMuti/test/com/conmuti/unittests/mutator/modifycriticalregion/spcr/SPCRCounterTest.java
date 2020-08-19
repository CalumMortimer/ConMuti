package com.conmuti.unittests.mutator.modifycriticalregion.spcr;

import com.conmuti.conmuti.mutator.modifycriticalregion.skcr.SKCRCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.spcr.SPCRCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class SPCRCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public void testMethod(){\n" +
                "        System.out.println(\"outside scope\");\n" +
                "        synchronized(this){\n" +
                "            System.out.println(\"inside scope\");\n" +
                "            System.out.println(\"inside scope\");\n" +
                "            System.out.println(\"inside scope\");\n" +
                "        }\n" +
                "        System.out.println(\"outside scope\");\n" +
                "    }\n" +
                "}");
        SPCRCounter spcr = new SPCRCounter();
        spcr.visit(cu,null);
        assertEquals(1,spcr.getMutantCount());
    }
}