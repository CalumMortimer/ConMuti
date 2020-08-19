package com.conmuti.unittests.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPASemaphoreModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ELPASemaphoreModifierTest {

    @Test
    public void visit() {
        //acquire starting point
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquire();\r\n" +
                "    }\r\n" +
                "}\r\n");
        ELPASemaphoreModifier elpa = new ELPASemaphoreModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.tryAcquire();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquire();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquireUninterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());

        //tryacquire starting point
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.tryAcquire();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa = new ELPASemaphoreModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquireUninterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.tryAcquire();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquire();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());

        //acquireUninterruptibly starting point
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquireUninterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa = new ELPASemaphoreModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.tryAcquire();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquireUninterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem = new Semaphore(1);\r\n" +
                "        sem.acquire();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}