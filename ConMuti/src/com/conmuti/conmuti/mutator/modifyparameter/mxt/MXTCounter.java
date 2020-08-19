package com.conmuti.conmuti.mutator.modifyparameter.mxt;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts the number wait(), sleep(), join() or await() method calls
 */
public class MXTCounter extends MutationCounter {
    /**
     * A method to walk through all the method call expressions in the compilation unit,
     * count the number of wait(),sleep() join() and await() method call expressions,
     * and store them in the mutantCount parameter in the MutationCounter superclass
     *
     * @param mc - the current method call expression
     * @param arg - always null
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String exp = mc.getNameAsString();

        if(exp.equals("wait")||exp.equals("await")||exp.equals("join")||exp.equals("sleep")){
            if (!mc.getArguments().isEmpty()) {
                setMutantCount(getMutantCount() + 1);
            }
        }
    }
}
