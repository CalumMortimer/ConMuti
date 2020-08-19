package com.conmuti.unittests.mutator.modifycriticalregion.shcr;

import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRDownModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRUpModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class SHCRDownModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    public void testMethod() {\r\n" +
                "        System.out.println(\"outside scope\");\r\n" +
                "        synchronized (this) {\n" +
                "            System.out.println(\"inside scope\");\r\n" +
                "            System.out.println(\"inside scope\");\r\n" +
                "        }\r\n" +
                "        System.out.println(\"outside scope\");\r\n" +
                "    }\r\n" +
                "}");
        SHCRDownModifier shcr = new SHCRDownModifier();
        shcr.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n"+
                "    public void testMethod() {\r\n" +
                "        System.out.println(\"outside scope\");\r\n" +
                "        System.out.println(\"inside scope\");\r\n" +
                "        synchronized (this) {\r\n" +
                "            System.out.println(\"inside scope\");\r\n" +
                "            System.out.println(\"outside scope\");\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}