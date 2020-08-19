package com.conmuti.conmuti.mutator.modifykeyword.rsb;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which walks through a compilation unit and counts the number of synchronized statements
 */
public class RSBCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every synchronized statement which is found,
     * increments the mutantCount parameter in the MutationCounter superclass.
     *
     * @param ss - the current synchronized statement (synchronized block)
     * @param arg - void
     */
    @Override
    public void visit(SynchronizedStmt ss, Void arg){
        super.visit(ss,arg);
        setMutantCount(getMutantCount()+1);
    }
}
