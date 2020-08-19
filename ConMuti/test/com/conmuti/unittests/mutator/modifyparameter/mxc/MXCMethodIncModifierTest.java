package com.conmuti.unittests.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCMethodDecModifier;
import com.conmuti.conmuti.mutator.modifyparameter.mxc.MXCMethodIncModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXCMethodIncModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        MXCMethodIncModifier mxc = new MXCMethodIncModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        s.acquire(2);\r\n" +
                "        s.release();\r\n" +
                "        s.tryAcquire();\r\n" +
                "        s.acquireUninterruptibly();\r\n" +
                "        s.acquire(10);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(1);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release(2);\r\n" +
                "        s.tryAcquire();\r\n" +
                "        s.acquireUninterruptibly();\r\n" +
                "        s.acquire(10);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(2);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s = new Semaphore(10);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n"+
                "        s.tryAcquire(2);\r\n" +
                "        s.acquireUninterruptibly();\r\n" +
                "        s.acquire(10);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(3);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
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
                "        s.acquireUninterruptibly(2);\r\n" +
                "        s.acquire(10);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(4);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
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
                "        s.acquire((10) + 1);\r\n" +
                "        s.release(10);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(5);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
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
                "        s.release((10) + 1);\r\n" +
                "        s.tryAcquire(10);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(6);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
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
                "        s.tryAcquire((10) + 1);\r\n" +
                "        s.acquireUninterruptibly(10);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        mxc.setMutationNumber(7);
        mxc.setMutationsVisited(0);
        mxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
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
                "        s.acquireUninterruptibly((10) + 1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}