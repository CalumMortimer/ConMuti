package com.conmuti.unittests.mutator.switchobjects.eelo;

import com.conmuti.conmuti.mutator.switchobjects.eelo.EELOCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class EELOCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.locks.Lock;\n" +
                "import java.util.concurrent.locks.ReentrantLock;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() {\n" +
                "        Lock l1 = new ReentrantLock();\n" +
                "        Lock l2 = new ReentrantLock();\n" +
                "        Lock l3 = new ReentrantLock();\n" +
                "\n" +
                "        l1.lock();\n" +
                "        l2.lock();\n" +
                "        l3.lock();\n" +
                "    }\n" +
                "}");
        EELOCounter eelo = new EELOCounter();
        eelo.visit(cu,null);
        assertEquals(3,eelo.getMutantCount());
    }
}