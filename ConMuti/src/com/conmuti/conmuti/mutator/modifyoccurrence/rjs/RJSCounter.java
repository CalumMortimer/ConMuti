package com.conmuti.conmuti.mutator.modifyoccurrence.rjs;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts the number of join method call expressions
 */
public class RJSCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every join method call expression
     * increments the mutantCount parameter
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("join"))){
            setMutantCount(getMutantCount() + 1);
        }
    }
}
