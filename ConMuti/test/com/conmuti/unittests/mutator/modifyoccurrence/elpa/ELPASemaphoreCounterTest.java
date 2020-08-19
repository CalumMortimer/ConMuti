package com.conmuti.unittests.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPASemaphoreCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ELPASemaphoreCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        \r\n" +
                "        sem.tryAcquire();\r\n" +
                "        sem.acquire();\r\n" +
                "        sem.acquireUninterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        ELPASemaphoreCounter elpasemaphore = new ELPASemaphoreCounter();
        elpasemaphore.visit(cu,null);
        assertEquals(6,elpasemaphore.getMutantCount());
    }
}