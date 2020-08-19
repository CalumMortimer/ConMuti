package com.conmuti.conmuti.mutator.modifykeyword.rsb;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which removes the synchronized part of a synchronized block in a compilation unit
 */
public class RSBModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the synchronized statement is marked as the current method for mutation, the BlockStatement object in the synchronized
     * {} parenthesis is returned. This is the child node which is always at position 1 within a synchronized statement.
     *
     * The line reference and method name of the synchronized statement mutant is set in the
     * MutationModifier superclass.
     *
     * If the current synchronized statement is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     * @return - the child node at position 1 of the synchronized statements children (the BlockStatement object to be executed),
     * or, if the synchronized statement is not to be updated, the synchronized statement
     */
    @Override
    public Node visit(SynchronizedStmt ss, Void arg){
        super.visit(ss,arg);
        if (getMutationsVisited()==getMutationNumber()) {
            setMutantLineReference(getNodeLineRef(ss));
            setMethodName(getMethodNameFromNode(ss));
            return ss.getChildNodes().get(1);
        }
        setMutationsVisited(getMutationsVisited() + 1);
        return ss;
    }
}
