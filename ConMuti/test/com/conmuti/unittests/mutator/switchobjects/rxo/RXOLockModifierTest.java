package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOLockCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOLockModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOLockModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "import java.util.concurrent.locks.ReentrantReadWriteLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        ReentrantReadWriteLock l2 = new ReentrantReadWriteLock();\r\n" +
                "        l1.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        RXOLockModifier rxo = new RXOLockModifier();
        rxo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "import java.util.concurrent.locks.ReentrantReadWriteLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        ReentrantReadWriteLock l2 = new ReentrantReadWriteLock();\r\n" +
                "        l2.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}