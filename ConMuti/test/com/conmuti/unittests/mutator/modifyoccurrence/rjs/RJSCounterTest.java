package com.conmuti.unittests.mutator.modifyoccurrence.rjs;

import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RJSCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "    }\r\n" +
                "}\r\n");
        RJSCounter rjs = new RJSCounter();
        rjs.visit(cu,null);
        assertEquals(1,rjs.getMutantCount());
    }
}