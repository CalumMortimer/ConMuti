package com.conmuti.conmuti.mutator.modifyoccurrence.rcxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts the number of lock, unlock, signal, signalAll, acquire,
 * release and countDown method call expressions
 */
public class RCXCCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every lock, unlock, signal, signalAll, acquire,
     * release and countDown method call expression,
     * increments the mutantCount parameter
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("lock"))||(name.equals("unlock"))||(name.equals("signal"))||(name.equals("signalAll"))||(name.equals("acquire"))||(name.equals("release"))||(name.equals("countDown"))){
            setMutantCount(getMutantCount() + 1);
        }
    }
}
