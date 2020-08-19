package com.conmuti.conmuti.mutator.modifycriticalregion.shcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to count the number of synchronized statements which have scope to move the
 * critical region further up a parent child node list. i.e. the parent BlockStmt has
 * an additional child above the synchronized statement
 */
public class SHCRUpCounter extends MutationCounter {
    /**
     * A method which counts the possible potential SHCR up mutations in synchronized statements
     * within the compilation unit.
     * For a shift upwards to be possible, the child block statement of the synchronized
     * statement must contain at least one statement, and the synchronized statement
     * cannot be at the start of its parent block statement.
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     */
    @Override
    public void visit(SynchronizedStmt ss,Void arg){
        super.visit(ss,arg);
        if (ss.getChildNodes().get(1).getChildNodes().size()>0){
            int location = getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
            if (location!=0){
                setMutantCount(getMutantCount()+1);
            }
        }
    }
}

