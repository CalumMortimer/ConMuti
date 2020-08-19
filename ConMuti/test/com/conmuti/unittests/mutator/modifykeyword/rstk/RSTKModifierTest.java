package com.conmuti.unittests.mutator.modifykeyword.rstk;

import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSTKModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public static synchronized void testMethod() {\r\n" +
                "    }\r\n" +
                "}\r\n");
        RSTKModifier rstk = new RSTKModifier();
        rstk.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public synchronized void testMethod() {\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}