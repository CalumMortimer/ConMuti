package com.conmuti.unittests.mutator.modifyparameter.mxt;

import com.conmuti.conmuti.mutator.modifyparameter.mxt.MXTCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MXTCounterTest {

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
                "    public void testMethod() throws InterruptedException, BrokenBarrierException, TimeoutException {\n" +
                "        Thread t = new Thread();\n" +
                "        t.wait(10);\n" +
                "        t.join(10);\n" +
                "        t.sleep(10);\n" +
                "        CyclicBarrier c = new CyclicBarrier(10);\n" +
                "        c.await(10, TimeUnit.MILLISECONDS);\n" +
                "    }\n" +
                "}");
        MXTCounter mxt = new MXTCounter();
        mxt.visit(cu,null);
        assertEquals(4,mxt.getMutantCount());
    }
}