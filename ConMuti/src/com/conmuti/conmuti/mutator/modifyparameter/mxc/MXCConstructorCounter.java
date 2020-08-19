package com.conmuti.conmuti.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

/**
 * A class which walks through a compilation unit and counts the number of semaphore latch and barrier objects created
 */
public class MXCConstructorCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every semaphore, latch or barrier object created,
     * increments the mutantCount parameter
     *
     * @param oc - the current object creation expression
     * @param arg - void
     */
    @Override
    public void visit(ObjectCreationExpr oc, Void arg){
        super.visit(oc,arg);
        String name = oc.getType().getName().asString();
        if ((name.equals("Semaphore"))||(name.equals("CountDownLatch"))||(name.equals("CyclicBarrier"))){
            setMutantCount(getMutantCount()+1);
        }
    }
}
