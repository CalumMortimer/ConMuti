package com.conmuti.unittests.mutator.modifyoccurrence.rtxc;

import com.conmuti.conmuti.mutator.modifyoccurrence.rtxc.RTXCCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rtxc.RTXCModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RTXCModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        RTXCModifier rtxc = new RTXCModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        rtxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.join();\r\n" +
                "        t.yield();\r\n" +
                "        t.notify();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        rtxc.setMutationNumber(1);
        rtxc.setMutationsVisited(0);
        rtxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait();\r\n" +
                "        t.yield();\r\n" +
                "        t.notify();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        rtxc.setMutationNumber(2);
        rtxc.setMutationsVisited(0);
        rtxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait();\r\n" +
                "        t.join();\r\n" +
                "        t.notify();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        rtxc.setMutationNumber(3);
        rtxc.setMutationsVisited(0);
        rtxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait();\r\n" +
                "        t.join();\r\n" +
                "        t.yield();\r\n" +
                "        t.notifyAll();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
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
        rtxc.setMutationNumber(4);
        rtxc.setMutationsVisited(0);
        rtxc.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Thread t = new Thread();\r\n" +
                "        t.wait();\r\n" +
                "        t.join();\r\n" +
                "        t.yield();\r\n" +
                "        t.notify();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}