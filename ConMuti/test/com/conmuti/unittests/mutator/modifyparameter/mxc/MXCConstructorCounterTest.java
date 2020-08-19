package com.conmuti.unittests.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCConstructorCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXCConstructorCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        CyclicBarrier b = new CyclicBarrier(10);\r\n" +
                "        CountDownLatch l = new CountDownLatch(10);\r\n" +
                "        \r\n" +
                "    }\r\n" +
                "}\r\n");
        MXCConstructorCounter mxc = new MXCConstructorCounter();
        mxc.visit(cu,null);
        assertEquals(3,mxc.getMutantCount());
    }
}