package com.conmuti.unittests.mutator.modifycriticalregion;

import com.conmuti.conmuti.mutator.modifycriticalregion.Movers;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoversTest {

    @Test
    public void addStatementFixed() {
        BlockStmt bs = new BlockStmt();
        MethodCallExpr ex1 = new MethodCallExpr();
        MethodCallExpr ex2 = new MethodCallExpr();
        MethodCallExpr findMe = new MethodCallExpr(null,"findMe");
        bs.addStatement(ex1);
        bs.addStatement(ex2);
        Movers.addStatementFixed(0,bs,findMe);
        assertEquals("findMe",((MethodCallExpr) ((ExpressionStmt) bs.getChildNodes().get(0)).getChildNodes().get(0)).getName().asString());
        Movers.addStatementFixed(2,bs,findMe);
        assertEquals("findMe",((MethodCallExpr) ((ExpressionStmt) bs.getChildNodes().get(2)).getChildNodes().get(0)).getName().asString());
        Movers.addStatementFixed(4,bs,findMe);
        assertEquals("findMe",((MethodCallExpr) ((ExpressionStmt) bs.getChildNodes().get(4)).getChildNodes().get(0)).getName().asString());
    }

    @Test
    public void moveFirstOutside() {
        //test integrated within SHCRDownModifierTest & SKCRModifierTest
    }

    @Test
    public void moveNextInside() {
        //test integrated within SHCRDownModifierTest & EXCRModifierTest
    }

    @Test
    public void movePreviousInside() {
        //test integrated within SHCRUpModifierTest & EXCRModifierTest
    }

    @Test
    public void moveLastOutside() {
        //test integrated within SHCRUpModifierTest & SHCRModifierTest
    }

    @Test
    public void synchronize() {
        //test integrated within SPCRModifierTest
    }
}