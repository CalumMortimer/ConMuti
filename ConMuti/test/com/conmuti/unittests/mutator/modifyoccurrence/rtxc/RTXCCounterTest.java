package com.conmuti.unittests.mutator.modifyoccurrence.rtxc;

import com.conmuti.conmuti.mutator.modifyoccurrence.rtxc.RTXCCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RTXCCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait();\r\n" +
                "        t.join();\r\n" +
                "        t.yield();\r\n" +
                "        t.notify();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n");
        RTXCCounter rtxc = new RTXCCounter();
        rtxc.visit(cu,null);
        assertEquals(5,rtxc.getMutantCount());
    }
}