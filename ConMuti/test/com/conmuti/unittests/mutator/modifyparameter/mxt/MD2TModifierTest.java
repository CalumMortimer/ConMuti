package com.conmuti.unittests.mutator.modifyparameter.mxt;

import com.conmuti.conmuti.mutator.modifyparameter.mxt.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MD2TModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        MD2TModifier mxt = new MD2TModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n");
        mxt.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait((10) / 2);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n");
        mxt.setMutationNumber(1);
        mxt.setMutationsVisited(0);
        mxt.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join((10) / 2);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n");
        mxt.setMutationNumber(2);
        mxt.setMutationsVisited(0);
        mxt.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep((10) / 2);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n");
        mxt.setMutationNumber(3);
        mxt.setMutationsVisited(0);
        mxt.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait(10);\r\n" +
                "        t.join(10);\r\n" +
                "        t.sleep(10);\r\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\r\n" +
                "        c.await((10) / 2, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}