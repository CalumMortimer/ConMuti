package com.conmuti.conmuti.mutator.modifycriticalregion.skcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to count the number of synchronized statements which have at least 3
 * child nodes and therefore the critical region can be reduced by one statement
 * above and below the critical region. Below this child list size the mutation
 * operator would have the same effect as the RSB mutation operator.
 */
public class SKCRCounter extends MutationCounter {
    /**
     * A method which counts the possible potential SKCR mutations in synchronized statements
     * within the compilation unit.
     * For a reduction to be possible, there must be 3 statements or expressions located inside
     * the syncrhonized statement
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     */
    @Override
    public void visit(SynchronizedStmt ss,Void arg){
        super.visit(ss,arg);
        if (ss.getChildNodes().get(1).getChildNodes().size()>=3)
            setMutantCount(getMutantCount()+1);
    }
}
