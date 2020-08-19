package com.conmuti.unittests.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCConstructorCounter;
import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCConstructorDecModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXCConstructorDecModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        MXCConstructorDecModifier mxc = new MXCConstructorDecModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
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
                "    }\r\n" +
                "}\r\n");
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore((10) - 1);\r\n" +
                "        CyclicBarrier b = new CyclicBarrier(10);\r\n" +
                "        CountDownLatch l = new CountDownLatch(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
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
                "    }\r\n" +
                "}\r\n");
        mxc.setMutationsVisited(0);
        mxc.setMutationNumber(1);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        CyclicBarrier b = new CyclicBarrier((10) - 1);\r\n" +
                "        CountDownLatch l = new CountDownLatch(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
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
                "    }\r\n" +
                "}\r\n");
        mxc.setMutationsVisited(0);
        mxc.setMutationNumber(2);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        CyclicBarrier b = new CyclicBarrier(10);\r\n" +
                "        CountDownLatch l = new CountDownLatch((10) - 1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}