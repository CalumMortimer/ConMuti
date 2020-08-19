package com.conmuti.unittests.mutator.modifykeyword.astk;

import com.conmuti.conmuti.mutator.modifycriticalregion.spcr.SPCRModifier;
import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class ASTKModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public synchronized void testMethod() {\r\n" +
                "    }\r\n" +
                "}\r\n");
        ASTKModifier astk = new ASTKModifier();
        astk.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n" +
                "    public static synchronized void testMethod() {\r\n" +
                "    }\r\n" +
                "}\r\n",cu.toString());
    }
}