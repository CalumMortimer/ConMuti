package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOSemaphoreCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOSemaphoreModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOSemaphoreModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s1 = new Semaphore(1);\r\n" +
                "        Semaphore s2 = new Semaphore(1);\r\n" +
                "        s1.acquire(1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        RXOSemaphoreModifier rxo = new RXOSemaphoreModifier();
        rxo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore s1 = new Semaphore(1);\r\n" +
                "        Semaphore s2 = new Semaphore(1);\r\n" +
                "        s2.acquire(1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}