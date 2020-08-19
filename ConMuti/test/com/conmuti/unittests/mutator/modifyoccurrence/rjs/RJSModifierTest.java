package com.conmuti.unittests.mutator.modifyoccurrence.rjs;

import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RJSModifierTest {

    @Test
    public void visit() {
        RJSModifier rjs = new RJSModifier();
        CompilationUnit cu = null;
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.join(1);\r\n" +
                "        t.join(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        rjs.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.sleep(10000);\r\n" +
                "        t.join(1);\r\n" +
                "        t.join(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.join(1);\r\n" +
                "        t.join(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        rjs.setMutationsVisited(0);
        rjs.setMutationNumber(1);
        rjs.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.sleep(1);\r\n" +
                "        t.join(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.join(1);\r\n" +
                "        t.join(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        rjs.setMutationsVisited(0);
        rjs.setMutationNumber(2);
        rjs.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.join(1);\r\n" +
                "        t.sleep(1, 1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}