package com.conmuti.conmuti.mutator.modifykeyword.rsk;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * A class which removes the synchronized attribute of one method declaration in a compilation unit
 */
public class RSKModifier extends MutationModifier {
    /**
     * A method which walks through all the method declarations in the compilation unit.
     * If the method declaration is synchronized, and marked as the current method for mutation,
     * the method declaration is made non-synchronized.
     *
     * The line reference and method name of the mutant is set in the
     * MutationModifier superclass.
     *
     * If the current method is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param md - the current method declaration
     * @param arg - always null
     * @return - the method declaration with any updates applied
     */
    @Override
    public MethodDeclaration visit(MethodDeclaration md, Void arg){
        super.visit(md,arg);
        if (md.isSynchronized()){
            if (getMutationsVisited()==getMutationNumber()) {
                md.setSynchronized(false);
                setMutantLineReference(md.getName().getBegin().get().line);
                setMethodName(md.getDeclarationAsString(false,false,false));
            }
            setMutationsVisited(getMutationsVisited()+1);
        }
        return md;
    }
}
