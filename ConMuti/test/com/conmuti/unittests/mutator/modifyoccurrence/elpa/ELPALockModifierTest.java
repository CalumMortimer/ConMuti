package com.conmuti.unittests.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ELPALockModifierTest {

    @Test
    public void visit() {
        //lock starting point
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        ELPALockModifier elpa = new ELPALockModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.tryLock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lockInterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());

        //tryLock starting point
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.tryLock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa = new ELPALockModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lockInterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.tryLock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());

        //lockInterruptibly starting point
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lockInterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa = new ELPALockModifier();
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.tryLock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lockInterruptibly();\r\n" +
                "    }\r\n" +
                "}\r\n");
        elpa.setMutationNumber(1);
        elpa.setMutationsVisited(0);
        elpa.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        l.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}