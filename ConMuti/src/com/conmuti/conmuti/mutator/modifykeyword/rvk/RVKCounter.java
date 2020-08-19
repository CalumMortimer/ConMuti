package com.conmuti.conmuti.mutator.modifykeyword.rvk;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.body.FieldDeclaration;

/**
 * A class which walks through a compilation unit and counts the number of volatile keywords
 */
public class RVKCounter extends MutationCounter {
    /**
     * A method to walk through all the field declarations in the compilation unit and count the number of volatile
     * field declarations
     *
     * @param fd - the current field declaration
     * @param arg - always null
     */
    @Override
    public void visit(FieldDeclaration fd, Void arg){
        super.visit(fd,arg);
        if (fd.isVolatile()) {
            this.setMutantCount(this.getMutantCount()+1);
        }
    }
}
