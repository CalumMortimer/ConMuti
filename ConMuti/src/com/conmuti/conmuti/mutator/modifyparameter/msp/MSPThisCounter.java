package com.conmuti.conmuti.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which walks through a compilation unit and counts the number of synchronized statements which do not
 * use the "this" keyword as a lock
 */
public class MSPThisCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every synchronized statement which is found which uses
     * a "this" keyword as the lock, increments the mutantCount parameter in the MutationCounter superclass.
     *
     * @param ss - the current synchronized statement (synchronized block)
     * @param arg - void
     */
    @Override
    public void visit(SynchronizedStmt ss, Void arg){
        super.visit(ss,arg);
        if (!(ss.getChildNodes().get(0) instanceof ThisExpr)){
            setMutantCount(getMutantCount()+1);
        }
    }
}
