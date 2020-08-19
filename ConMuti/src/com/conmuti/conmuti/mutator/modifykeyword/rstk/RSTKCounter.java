package com.conmuti.conmuti.mutator.modifykeyword.rstk;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * A class which walks through a compilation unit and counts the number of synchronized methods with a static keyword
 */
public class RSTKCounter extends MutationCounter {

    /**
     * A method to walk through all the method declarations in the compilation unit, count the synchronized methods with a static keyword, and store them in the
     * mutantCount parameter
     *
     * @param md - the current method declaration
     * @param arg - always null
     */
    @Override
    public void visit(MethodDeclaration md, Void arg){
        super.visit(md,arg);
        if (md.isStatic()&&md.isSynchronized()) {
            this.setMutantCount(this.getMutantCount()+1);
        }
    }
}
