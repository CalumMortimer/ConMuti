package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOSemaphoreCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOSemaphoreCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.Semaphore;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        Semaphore s1 = new Semaphore(1);\n" +
                "        Semaphore s2 = new Semaphore(1);\n" +
                "        s1.acquire(1);\n" +
                "    }\n" +
                "}");
        RXOSemaphoreCounter rxo = new RXOSemaphoreCounter();
        rxo.visit(cu,null);
        assertEquals(1,rxo.getMutantCount());
    }
}