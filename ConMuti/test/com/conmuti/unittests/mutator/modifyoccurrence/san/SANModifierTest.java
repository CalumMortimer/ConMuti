package com.conmuti.unittests.mutator.modifyoccurrence.san;

import com.conmuti.conmuti.mutator.modifyoccurrence.san.SANCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.san.SANModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class SANModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.atomic.AtomicInteger;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    AtomicInteger i = new AtomicInteger(1);\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        i.getAndSet(2);\r\n" +
                "    }\r\n" +
                "}\r\n");
        SANModifier san = new SANModifier();
        san.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.atomic.AtomicInteger;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    AtomicInteger i = new AtomicInteger(1);\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        i.get();\r\n"+
                "        i.set(2);\r\n"+
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}