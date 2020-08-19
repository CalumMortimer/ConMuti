package com.conmuti.unittests.mutator.modifyoccurrence.rcxc;

import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RCXCCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.CountDownLatch;\n" +
                "import java.util.concurrent.Semaphore;\n" +
                "import java.util.concurrent.locks.Condition;\n" +
                "import java.util.concurrent.locks.Lock;\n" +
                "import java.util.concurrent.locks.ReentrantLock;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        Lock l = new ReentrantLock();\n" +
                "        l.lock();\n" +
                "        l.unlock();\n" +
                "        Thread t = new Thread();\n" +
                "        Condition c = l.newCondition();\n" +
                "        c.signal();\n" +
                "        c.signalAll();\n" +
                "        Semaphore s = new Semaphore(1);\n" +
                "        s.acquire();\n" +
                "        s.release();\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\n" +
                "        cd.countDown();\n" +
                "    }\n" +
                "}\n");
        RCXCCounter rcxc = new RCXCCounter();
        rcxc.visit(cu,null);
        assertEquals(7,rcxc.getMutantCount());
    }
}