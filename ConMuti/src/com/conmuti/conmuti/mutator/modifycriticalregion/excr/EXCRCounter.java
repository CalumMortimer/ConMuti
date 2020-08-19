package com.conmuti.conmuti.mutator.modifycriticalregion.excr;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to count the number of synchronized statements which have scope to expand the
 * critical region to consume one statement above and one statement below the synchronized statement
 */
public class EXCRCounter extends MutationCounter {
    /**
     * A method which counts the possible potential EXCR mutations in synchronized statements
     * within the compilation unit.
     * For an expansion to be possible, there must be statements or expressions located on
     * both sides of the synchronized statement - before and after
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     */
    @Override
    public void visit(SynchronizedStmt ss,Void arg){
        super.visit(ss,arg);
        int location = getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
        if (ss.getParentNode().get().getChildNodes().size()>(location+1))
            if (location!=0)
                setMutantCount(getMutantCount()+1);
    }
}
