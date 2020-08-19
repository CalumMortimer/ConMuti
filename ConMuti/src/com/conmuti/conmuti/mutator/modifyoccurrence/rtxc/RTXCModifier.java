package com.conmuti.conmuti.mutator.modifyoccurrence.rtxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * A class which removes wait, join, sleep, yield, notify and notifyAll method call expressions from a compilation unit
 */
public class RTXCModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression name is wait, join, sleep, yield, notify or notifyAll,
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
        if ((name.equals("wait"))||(name.equals("join"))||(name.equals("sleep"))||(name.equals("yield"))||(name.equals("notify"))||(name.equals("notifyAll"))) {
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