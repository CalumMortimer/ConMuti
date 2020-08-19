package com.conmuti.conmuti.mutator.modifykeyword.astk;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * A class which walks through a compilation unit and counts the number of synchronized methods without a static keyword
 */
public class ASTKCounter extends MutationCounter {
    /**
     * A method to walk through all the methods in the compilation unit, count the non-static synchronized methods and store the number
     * of methods in the mutantCount parameter in the MutationCounter superclass
     *
     * @param md - the current method declaration
     * @param arg - always null
     */
    @Override
    public void visit(MethodDeclaration md, Void arg){
        super.visit(md,arg);
        if ((!md.isStatic())&&md.isSynchronized()) {
            this.setMutantCount(this.getMutantCount()+1);
        }
    }
}
