package com.conmuti.unittests.mutator.modifyparameter.mbr;

import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MBRCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.CyclicBarrier;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        CyclicBarrier b = new CyclicBarrier(1,new Runnable(){public void run(){}});\n" +
                "    }\n" +
                "}");
        MBRCounter mbr = new MBRCounter();
        mbr.visit(cu,null);
        assertEquals(1,mbr.getMutantCount());
    }
}