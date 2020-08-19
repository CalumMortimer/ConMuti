package com.conmuti.conmuti.mutator.modifyparameter.mbr;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

/**
 * A class which walks through a compilation unit and counts the number of two parameter cyclic barrier object creation
 * expressions there are
 */
public class MBRCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every two-parameter cyclic barrier object creation expressions
     * increments the mutantCount parameter
     *
     * @param oc - the current object creation expression
     * @param arg - void
     */
    @Override
    public void visit(ObjectCreationExpr oc, Void arg){
        super.visit(oc,arg);
        String name = oc.getType().getName().asString();
        if (name.equals("CyclicBarrier")){
            if (oc.getChildNodes().size()==3) {
                setMutantCount(getMutantCount() + 1);
            }
        }
    }
}
