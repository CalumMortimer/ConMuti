package com.conmuti.unittests.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ELPALockCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        \r\n" +
                "        l.lock();\n" +
                "        l.tryLock();\n" +
                "        l.lockInterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        ELPALockCounter elpalock = new ELPALockCounter();
        elpalock.visit(cu,null);
        assertEquals(6,elpalock.getMutantCount());
    }
}