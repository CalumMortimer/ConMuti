package com.conmuti.conmuti.mutator.modifyoccurrence.rcxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which removes lock, unlock, signal, signalAll, acquire,
 * release and countDown method call expressions from a compilation unit
 */
public class RCXCModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression name is lock, unlock, signal, signalAll, acquire,
     * release or countDown,
     * and marked as the current concurrency object for mutation, the method call expression is removed
     *
     * The line reference and method name of the mutant is set in the
     * MutationModifier superclass.
     *
     * If the current expression is a method call expression with the correct name
     * but is not marked for mutation,
     * no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param mc  - the current method call expression
     * @param arg - always null
     * @return - the method call expression with any modifications applied
     */
    @Override
    public Node visit(MethodCallExpr mc, Void arg) {
        super.visit(mc, arg);
        String name = mc.getName().asString();
        if ((name.equals("lock"))||(name.equals("unlock"))||(name.equals("signal"))||(name.equals("signalAll"))||(name.equals("acquire"))||(name.equals("release"))||(name.equals("countDown"))) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(mc));
                setMethodName(getMethodNameFromNode(mc));
                mc = null;
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return mc;
    }
}