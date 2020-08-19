package com.conmuti.unittests.mutator.modifykeyword.rvk;

import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rvk.RVKModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RVKModifierTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    volatile int myInt = 0;\r\n" +
                "}\r\n");
        RVKModifier rvk = new RVKModifier();
        rvk.visit(cu,null);
        assertEquals("package com.example.example;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "\r\n"+
                "    int myInt = 0;\r\n" +
                "}\r\n",cu.toString());
    }
}