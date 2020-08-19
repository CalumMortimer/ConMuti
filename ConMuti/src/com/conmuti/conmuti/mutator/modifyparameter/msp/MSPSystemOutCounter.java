package com.conmuti.conmuti.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which walks through a compilation unit and counts the number of synchronized statements which do not
 * use the "System.out" FieldAccessExpr as a lock
 */
public class MSPSystemOutCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every synchronized statement which is found which uses
     * a "System.out" FieldAccessExpr as the lock, increments the mutantCount parameter in the MutationCounter superclass.
     *
     * @param ss - the current synchronized statement (synchronized block)
     * @param arg - void
     */
    @Override
    public void visit(SynchronizedStmt ss, Void arg) {
        super.visit(ss, arg);
        if (ss.getChildNodes().get(0) instanceof FieldAccessExpr) {
            if (((FieldAccessExpr) ss.getChildNodes().get(0)).getNameAsString().equals("System.out")) {
                //do nothing
            }
            else {
                setMutantCount(getMutantCount()+1);
            }
        }
        else{
            setMutantCount(getMutantCount()+1);
        }
    }
}