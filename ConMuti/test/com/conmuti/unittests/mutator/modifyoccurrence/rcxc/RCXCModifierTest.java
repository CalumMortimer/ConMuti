package com.conmuti.unittests.mutator.modifyoccurrence.rcxc;

import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RCXCModifierTest {

    @Test
    public void visit() {
        RCXCModifier rcxc = new RCXCModifier();
        CompilationUnit cu = null;
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(1);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(2);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(3);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(4);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(5);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "        cd.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        rcxc.setMutationNumber(6);
        rcxc.setMutationsVisited(0);
        rcxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "import java.util.concurrent.locks.Condition;\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "        l.unlock();\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        Condition c = l.newCondition();\r\n" +
                "        c.signal();\r\n" +
                "        c.signalAll();\r\n" +
                "        Semaphore s = new Semaphore(1);\r\n" +
                "        s.acquire();\r\n" +
                "        s.release();\r\n" +
                "        CountDownLatch cd = new CountDownLatch(1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}