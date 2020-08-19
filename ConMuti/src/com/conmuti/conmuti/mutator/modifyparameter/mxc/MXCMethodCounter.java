package com.conmuti.conmuti.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts the number of semaphore method calls
 * which can be incremented or decremented in an MXC modification
 */
public class MXCMethodCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and counts the number of method call expressions with the following
     * names:- "acquire", "acquireUninterruptibly", "release", "tryAcquire"
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("acquire"))||(name.equals("acquireUninterruptibly"))||(name.equals("release"))||(name.equals("tryAcquire"))){
            setMutantCount(getMutantCount()+1);
        }
    }
}
