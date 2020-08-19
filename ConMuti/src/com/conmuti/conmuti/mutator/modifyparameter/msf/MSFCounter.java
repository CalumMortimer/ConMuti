package com.conmuti.conmuti.mutator.modifyparameter.msf;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

/**
 * A class which walks through a compilation unit and counts the number of semaphore objects created
 */
public class MSFCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every semaphore object creation expression created
     * increments the mutantCount parameter
     *
     * @param oc - the current object creation expression
     * @param arg - void
     */
    @Override
    public void visit(ObjectCreationExpr oc, Void arg){
        super.visit(oc,arg);
        if (oc.getType().getName().asString().equals("Semaphore")){
            setMutantCount(getMutantCount()+1);
        }
    }
}
