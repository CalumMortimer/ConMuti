package com.conmuti.conmuti.mutator.modifyoccurrence.rtxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts the number of wait, sleep, yield, notify and notifyall method
 * call expressions
 */
public class RTXCCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every wait, sleep, yield, notify and notifyall method call expression,
     * increments the mutantCount parameter
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("wait"))||(name.equals("join"))||(name.equals("sleep"))||(name.equals("yield"))||(name.equals("notify"))||(name.equals("notifyAll"))){
            setMutantCount(getMutantCount() + 1);
        }
    }
}
