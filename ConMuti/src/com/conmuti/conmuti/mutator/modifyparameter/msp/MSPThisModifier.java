package com.conmuti.conmuti.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which changes the object inside the parentheses of a synchronized statement to "this" if the synchronized
 * statement is not already using this as a lock
 */
public class MSPThisModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the synchronized statement is marked as the current method for mutation, the object inside the synchronized
     * statement is changed to "this"
     *
     * The line reference and method name of the synchronized statement mutant is set in the
     * MutationModifier superclass.
     *
     * If the current synchronized statement is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     * @return - the synchronized statement with any modifications applied
     */
    @Override
    public Node visit(SynchronizedStmt ss, Void arg){
        super.visit(ss,arg);
        if (!(ss.getChildNodes().get(0) instanceof ThisExpr)){
            if (getMutationsVisited()==getMutationNumber()) {
                setMethodName(getMethodNameFromNode(ss));
                setMutantLineReference(getNodeLineRef(ss));
                ThisExpr newThis = new ThisExpr();
                ss.getChildNodes().get(0).replace(newThis);
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return ss;
    }
}
