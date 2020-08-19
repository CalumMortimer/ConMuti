package com.conmuti.unittests.mutator.modifyparameter.mbr;

import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRCounter;
import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MBRModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        CyclicBarrier b = new CyclicBarrier(1,new Runnable(){public void run(){}});\r\n" +
                "    }\r\n" +
                "}\r\n");
        MBRModifier mbr = new MBRModifier();
        mbr.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.CyclicBarrier;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public void testMethod() throws InterruptedException {\r\n" +
                "        CyclicBarrier b = new CyclicBarrier(1);\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}