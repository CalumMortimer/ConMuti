package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOLockCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOLockCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.locks.Lock;\n" +
                "import java.util.concurrent.locks.ReentrantLock;\n" +
                "import java.util.concurrent.locks.ReentrantReadWriteLock;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() {\n" +
                "        Lock l1 = new ReentrantLock();\n" +
                "        ReentrantReadWriteLock l2 = new ReentrantReadWriteLock();\n" +
                "        l1.lock();\n" +
                "    }\n" +
                "}\n");
        RXOLockCounter rxo = new RXOLockCounter();
        rxo.visit(cu,null);
        assertEquals(1,rxo.getMutantCount());
    }
}