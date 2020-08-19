package com.conmuti.unittests.mutator.modifykeyword.astk;

import com.conmuti.conmuti.mutator.modifycriticalregion.spcr.SPCRCounter;
import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ASTKCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        ASTKCounter astk = new ASTKCounter();
        astk.visit(cu,null);
        assertEquals(1,astk.getMutantCount());
    }
}