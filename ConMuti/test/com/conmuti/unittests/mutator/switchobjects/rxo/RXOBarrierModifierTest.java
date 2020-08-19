package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOBarrierCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOBarrierModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOBarrierModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, TimeoutException, BrokenBarrierException {\r\n" +
                "        CyclicBarrier b1 = new CyclicBarrier(1);\r\n" +
                "        CyclicBarrier b2 = new CyclicBarrier(1);\r\n" +
                "        b1.await(100, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n");
        RXOBarrierModifier rxo = new RXOBarrierModifier();
        rxo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.BrokenBarrierException;\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "import java.util.concurrent.TimeUnit;\r\n" +
                "import java.util.concurrent.TimeoutException;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException, TimeoutException, BrokenBarrierException {\r\n" +
                "        CyclicBarrier b1 = new CyclicBarrier(1);\r\n" +
                "        CyclicBarrier b2 = new CyclicBarrier(1);\r\n" +
                "        b2.await(100, TimeUnit.MILLISECONDS);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}