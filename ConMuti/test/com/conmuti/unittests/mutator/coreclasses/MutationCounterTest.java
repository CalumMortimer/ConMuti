package com.conmuti.unittests.mutator.coreclasses;

import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutationCounterTest {

    @Test
    public void getLocationOfChildNodeInParentList() {
        BlockStmt parent = new BlockStmt();
        ExpressionStmt ob1 = new ExpressionStmt();
        ExpressionStmt ob2 = new ExpressionStmt();
        ExpressionStmt ob3 = new ExpressionStmt();
        parent.addStatement(ob1);
        parent.addStatement(ob2);
        parent.addStatement(ob3);
        RSTKCounter c = new RSTKCounter();
        assertEquals(0,c.getLocationOfChildNodeInParentList(parent,ob1));
        assertEquals(1,c.getLocationOfChildNodeInParentList(parent,ob2));
        assertEquals(2,c.getLocationOfChildNodeInParentList(parent,ob3));
    }

    @Test
    public void getMutantCount() {
        RSTKCounter c = new RSTKCounter();
        assertEquals(0,c.getMutantCount());
    }

    @Test
    public void setMutantCount() {
        RSTKCounter c = new RSTKCounter();
        c.setMutantCount(5);
        assertEquals(5,c.getMutantCount());
    }
}