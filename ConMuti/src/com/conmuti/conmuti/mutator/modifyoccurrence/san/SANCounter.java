package com.conmuti.conmuti.mutator.modifyoccurrence.san;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which walks through a compilation unit and counts double the number of getAndSet methods call expressions there are
 */
public class SANCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every getAndSet method call expression that is found,
     * increments the mutantCount parameter
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public void visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String name = mc.getName().asString();
        if ((name.equals("getAndSet"))){
            setMutantCount(getMutantCount() + 1);
        }
    }
}
