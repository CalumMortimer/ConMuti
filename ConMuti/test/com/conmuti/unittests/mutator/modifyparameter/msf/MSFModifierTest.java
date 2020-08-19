package com.conmuti.unittests.mutator.modifyparameter.msf;

import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MSFModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        MSFModifier msf = new MSFModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem3 = new Semaphore(1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        msf.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem3 = new Semaphore(1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem3 = new Semaphore(1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        msf.setMutationsVisited(0);
        msf.setMutationNumber(1);
        msf.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem3 = new Semaphore(1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem3 = new Semaphore(1);\r\n" +
                "    }\r\n" +
                "}\r\n");
        msf.setMutationsVisited(0);
        msf.setMutationNumber(2);
        msf.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.Semaphore;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        Semaphore sem1 = new Semaphore(1, false);\r\n" +
                "        Semaphore sem2 = new Semaphore(1, true);\r\n" +
                "        Semaphore sem3 = new Semaphore(1, true);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}