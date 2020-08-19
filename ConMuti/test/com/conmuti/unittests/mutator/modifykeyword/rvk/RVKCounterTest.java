package com.conmuti.unittests.mutator.modifykeyword.rvk;

import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rvk.RVKCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RVKCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    volatile int myInt = 0;\r\n" +
                "}\r\n");
        RVKCounter rvk = new RVKCounter();
        rvk.visit(cu,null);
        assertEquals(1,rvk.getMutantCount());
    }
}