package com.conmuti.unittests.mutator.modifykeyword.rsb;

import com.conmuti.conmuti.mutator.modifycriticalregion.excr.EXCRCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rsb.RSBCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSBCounterTest {

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
                "        }\n" +
                "        System.out.println(\"outside scope\");\n" +
                "    }\n" +
                "}");
        RSBCounter rsb = new RSBCounter();
        rsb.visit(cu,null);
        assertEquals(1,rsb.getMutantCount());
    }
}