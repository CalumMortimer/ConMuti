package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOBarrierCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOBarrierCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.BrokenBarrierException;\n" +
                "import java.util.concurrent.CyclicBarrier;\n" +
                "import java.util.concurrent.TimeUnit;\n" +
                "import java.util.concurrent.TimeoutException;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException, TimeoutException, BrokenBarrierException {\n" +
                "        CyclicBarrier b1 = new CyclicBarrier(1);\n" +
                "        CyclicBarrier b2 = new CyclicBarrier(1);\n" +
                "         b1.await(100, TimeUnit.MILLISECONDS);\n" +
                "    }\n" +
                "}");
        RXOBarrierCounter rxo = new RXOBarrierCounter();
        rxo.visit(cu,null);
        assertEquals(1,rxo.getMutantCount());
    }
}