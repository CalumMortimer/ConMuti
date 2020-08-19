package com.conmuti.conmuti.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts double the number of acquire, acquireUninterruptibly
 * and tryAcquire method call expressions
 */
public class ELPASemaphoreCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every acquire, acquireUninterruptibly and tryAcquire method call expression
     * increments the mutantCount parameter by two
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("acquire"))||(name.equals("tryAcquire"))||(name.equals("acquireUninterruptibly"))){
            setMutantCount(getMutantCount() + 2);
        }
    }
}
