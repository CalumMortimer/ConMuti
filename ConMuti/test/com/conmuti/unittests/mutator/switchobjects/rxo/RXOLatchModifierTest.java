package com.conmuti.unittests.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOLatchCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.RXOLatchModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RXOLatchModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        CountDownLatch l1 = new CountDownLatch(1);\r\n" +
                "        CountDownLatch l2 = new CountDownLatch(1);\r\n" +
                "        l1.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n");
        RXOLatchModifier rxo = new RXOLatchModifier();
        rxo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CountDownLatch;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        CountDownLatch l1 = new CountDownLatch(1);\r\n" +
                "        CountDownLatch l2 = new CountDownLatch(1);\r\n" +
                "        l2.countDown();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}