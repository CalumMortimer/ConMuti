package com.conmuti.conmuti.mutator.modifykeyword.rvk;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.body.FieldDeclaration;

/**
 * A class which removes the volatile keyword from a field declaration in a compilation unit
 */
public class RVKModifier extends MutationModifier {
    /**
     * A method which walks through all the field declarations in the compilation unit.
     * If the field is volatile, and marked as the current field for mutation,
     * the field is set to non-volatile. The line reference of the mutant is set in the
     * MutationModifier superclass. The method name of the mutant is set to "N/A" because volatile fields
     * can only be set as global fields - otherwise the code is syntactically wrong.
     *
     * If the current method is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param fd - the current field declaration
     * @param arg - always null
     * @return - the field declaration with any updates applied
     */
    @Override
    public FieldDeclaration visit(FieldDeclaration fd, Void arg){
        super.visit(fd,arg);
        if (fd.isVolatile()){
            if (getMutationsVisited()==getMutationNumber()) {
                fd.setVolatile(false);
                setMutantLineReference(getNodeLineRef(fd));
                setMethodName("N/A");
            }
            setMutationsVisited(getMutationsVisited()+1);
        }
        return fd;
    }
}
