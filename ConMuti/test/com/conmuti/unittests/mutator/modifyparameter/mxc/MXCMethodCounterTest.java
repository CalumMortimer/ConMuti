package com.conmuti.unittests.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCMethodCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXCMethodCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        s.tryAcquire();\r\n" +
                "        s.acquireUninterruptibly();\r\n" +
                "        s.acquire(10);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n");
        MXCMethodCounter mxc = new MXCMethodCounter();
        mxc.visit(cu,null);
        assertEquals(8,mxc.getMutantCount());
    }
}