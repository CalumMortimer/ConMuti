package com.conmuti.unittests.mutator.modifyoccurrence.san;

import com.conmuti.conmuti.mutator.modifyoccurrence.san.SANCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class SANCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.atomic.AtomicInteger;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    AtomicInteger i = new AtomicInteger(1);\n" +
                "    \n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        i.getAndSet(2);\n" +
                "    }\n" +
                "}\n");
        SANCounter san = new SANCounter();
        san.visit(cu,null);
        assertEquals(1,san.getMutantCount());
    }
}