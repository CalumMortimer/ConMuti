package com.conmuti.unittests.mutator.modifykeyword.rsk;

import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rsk.RSKCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSKCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    public synchronized void testMethod(){\n" +
                "    }\n" +
                "}");
        RSKCounter rsk = new RSKCounter();
        rsk.visit(cu,null);
        assertEquals(1,rsk.getMutantCount());
    }
}