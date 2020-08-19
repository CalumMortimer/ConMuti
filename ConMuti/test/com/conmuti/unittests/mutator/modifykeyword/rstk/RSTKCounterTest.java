package com.conmuti.unittests.mutator.modifykeyword.rstk;

import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSTKCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public static synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        RSTKCounter rstk = new RSTKCounter();
        rstk.visit(cu,null);
        assertEquals(1,rstk.getMutantCount());
    }
}