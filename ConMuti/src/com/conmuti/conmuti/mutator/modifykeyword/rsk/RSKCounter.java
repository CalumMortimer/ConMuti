package com.conmuti.conmuti.mutator.modifykeyword.rsk;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * A class which walks through a compilation unit and counts the number of synchronized methods in the compilation unit
 */
public class RSKCounter extends MutationCounter {
    /**
     * A method to walk through all the methods in the compilation unit, count the synchronized methods, and store them in the
     * mutantCount parameter
     *
     * @param md - the current method declaration
     * @param arg - void
     */
    @Override
    public void visit(MethodDeclaration md, Void arg){
        super.visit(md,arg);
        if (md.isSynchronized()) {
            this.setMutantCount(this.getMutantCount()+1);
        }
    }
}
