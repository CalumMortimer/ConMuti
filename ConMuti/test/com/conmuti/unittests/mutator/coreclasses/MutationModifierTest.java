package com.conmuti.unittests.mutator.coreclasses;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKModifier;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutationModifierTest {

    @Test
    public void getMutationNumber() {
        RSTKModifier m = new RSTKModifier();
        assertEquals(0,m.getMutationNumber());
    }

    @Test
    public void getMutationsVisited() {
        RSTKModifier m = new RSTKModifier();
        assertEquals(0,m.getMutationsVisited());
    }

    @Test
    public void getMutantLineReference() {
        RSTKModifier m = new RSTKModifier();
        assertEquals(0,m.getMutantLineReference());
    }

    @Test
    public void getMethodName() {
        RSTKModifier m = new RSTKModifier();
        assertEquals("",m.getMethodName());
    }

    @Test
    public void setMutationNumber() {
        RSTKModifier m = new RSTKModifier();
        m.setMutationNumber(5);
        assertEquals(5,m.getMutationNumber());
    }

    @Test
    public void setMutationsVisited() {
        RSTKModifier m = new RSTKModifier();
        m.setMutationsVisited(5);
        assertEquals(5,m.getMutationsVisited());
    }

    @Test
    public void setMethodName() {
        RSTKModifier m = new RSTKModifier();
        m.setMethodName("method name");
        assertEquals("method name",m.getMethodName());
    }

    @Test
    public void setMutantLineReference() {
        RSTKModifier m = new RSTKModifier();
        m.setMutantLineReference(456);
        assertEquals(456,m.getMutantLineReference());
    }

    @Test
    public void getLocationOfChildNodeInParentList() {
        BlockStmt parent = new BlockStmt();
        ExpressionStmt ob1 = new ExpressionStmt();
        ExpressionStmt ob2 = new ExpressionStmt();
        ExpressionStmt ob3 = new ExpressionStmt();
        parent.addStatement(ob1);
        parent.addStatement(ob2);
        parent.addStatement(ob3);
        RSTKModifier m = new RSTKModifier();
        assertEquals(0,m.getLocationOfChildNodeInParentList(parent,ob1));
        assertEquals(1,m.getLocationOfChildNodeInParentList(parent,ob2));
        assertEquals(2,m.getLocationOfChildNodeInParentList(parent,ob3));
    }

    @Test
    public void getMethodNameFromNode() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    \n" +
                "    public void testMethod(){\n" +
                "        System.out.println(\"hello\");\n" +
                "    }\n" +
                "}");
        assertEquals("void testMethod()", MutationModifier.getMethodNameFromNode(cu.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(2).getChildNodes().get(0).getChildNodes().get(0)));
        cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    \n" +
                "    Object o = new Object();\n" +
                "    \n" +
                "    public void testMethod(){\n" +
                "    }\n" +
                "}\n");
        assertNull(MutationModifier.getMethodNameFromNode(cu.getChildNodes().get(1).getChildNodes().get(1)));
    }

    @Test
    public void getNodeLineRef() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "    \n" +
                "    Object o = new Object();\n" +
                "    \n" +
                "    public void testMethod(){\n" +
                "    }\n" +
                "}\n");
        assertEquals(5,MutationModifier.getNodeLineRef(cu.getChildNodes().get(1).getChildNodes().get(1)));
    }
}