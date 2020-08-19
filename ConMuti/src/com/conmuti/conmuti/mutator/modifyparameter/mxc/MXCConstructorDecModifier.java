package com.conmuti.conmuti.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.*;

/**
 * A class which decrements the initial values of barriers, latches and semaphores in a compilation unit
 */
public class MXCConstructorDecModifier extends MutationModifier {
    /**
     * A method which walks through all the object creations expressions in a compilation unit
     * If the object creation expression is a semaphore, countdownlatch or cyclicbarrer,
     * and marked as the current concurrency object for mutation
     * its initial value is decremented
     * <p>
     * The line reference and method name of the semaphore mutant is set in the
     * MutationModifier superclass.
     * <p>
     * If the current object creation expression is a semaphore, cyclicbarrier or countdownlatch,
     * but is not marked for mutation,
     * no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param oc  - the current object creation expression
     * @param arg - always null
     * @return - the object creation expression with any modifications applied
     */
    @Override
    public Node visit(ObjectCreationExpr oc, Void arg) {
        super.visit(oc, arg);
        String name = oc.getType().toString();
        if ((name.equals("Semaphore")) || (name.equals("CountDownLatch")) || (name.equals("CyclicBarrier"))) {
            if (getMutationNumber() == getMutationsVisited()) {
                Expression oldCount = (Expression) oc.getChildNodes().get(1).clone();
                BinaryExpr newCount = new BinaryExpr(new EnclosedExpr(oldCount),new IntegerLiteralExpr(1),BinaryExpr.Operator.MINUS);
                oc.replace(oc.getChildNodes().get(1), newCount);
                setMutantLineReference(getNodeLineRef(oc));
                setMethodName(getMethodNameFromNode(oc));
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return oc;
    }
}