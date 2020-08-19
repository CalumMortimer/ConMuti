package com.conmuti.conmuti.mutator.modifycriticalregion.spcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to count the number of synchronized statements which have at least 2
 * child nodes and therefore the critical region can be split.
 */
public class SPCRCounter extends MutationCounter {
    /**
     * A method which counts the possible potential SPCR mutations in synchronized statements
     * within the compilation unit.
     * For a split to be possible, there must be 2 statements or expressions located inside
     * the synchronized statement
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     */
    @Override
    public void visit(SynchronizedStmt ss,Void arg){
        super.visit(ss,arg);
        if (ss.getChildNodes().get(1).getChildNodes().size()>=2)
            setMutantCount(getMutantCount()+1);
    }
}
