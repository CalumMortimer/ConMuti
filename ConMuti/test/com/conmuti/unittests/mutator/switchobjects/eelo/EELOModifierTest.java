package com.conmuti.unittests.mutator.switchobjects.eelo;

import com.conmuti.conmuti.mutator.switchobjects.eelo.EELOCounter;
import com.conmuti.conmuti.mutator.switchobjects.eelo.EELOModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class EELOModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = null;
        EELOModifier eelo = new EELOModifier();
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l1.lock();\r\n" +
                "        l2.lock();\r\n" +
                "        l3.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        eelo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l2.lock();\r\n" +
                "        l1.lock();\r\n" +
                "        l3.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l1.lock();\r\n" +
                "        l2.lock();\r\n" +
                "        l3.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        eelo.setMutationNumber(1);
        eelo.setMutationsVisited(0);
        eelo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l3.lock();\r\n" +
                "        l2.lock();\r\n" +
                "        l1.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
        cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l1.lock();\r\n" +
                "        l2.lock();\r\n" +
                "        l3.lock();\r\n" +
                "    }\r\n" +
                "}\r\n");
        eelo.setMutationNumber(2);
        eelo.setMutationsVisited(0);
        eelo.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() {\r\n" +
                "        Lock l1 = new ReentrantLock();\r\n" +
                "        Lock l2 = new ReentrantLock();\r\n" +
                "        Lock l3 = new ReentrantLock();\r\n" +
                "        l1.lock();\r\n" +
                "        l3.lock();\r\n" +
                "        l2.lock();\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}