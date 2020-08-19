package com.conmuti.conmuti.mutator.modifyparameter.msf;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

/**
 * A class which changes the fairness of semaphores within a compilation unit
 */
public class MSFModifier extends MutationModifier {
    /**
     * A method which walks through all the object creations expressions in a compilation unit
     * If the object creation expression is a semaphore, and marked as the current semaphore for mutation,
     * its fairness is changed to the opposite of its current fairness
     * if the fairness value is absent it is set to true, as unfair semaphores are assumed in the short form constructor
     *
     * The line reference and method name of the semaphore mutant is set in the
     * MutationModifier superclass.
     *
     * If the current object creation expression is a semaphore, but is not marked for mutation,
     * no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param oc  - the current synchronized statement
     * @param arg - always null
     * @return - the synchronized statement with any modifications applied
     */
    @Override
    public Node visit(ObjectCreationExpr oc, Void arg) {
        super.visit(oc, arg);
        ObjectCreationExpr newExpression = oc.clone();
        if (oc.getType().toString().equals("Semaphore")) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(oc));
                setMethodName(getMethodNameFromNode(oc));
                if (oc.getChildNodes().size() == 3) {
                    if (!((BooleanLiteralExpr) oc.getChildNodes().get(2)).getValue()) {
                        oc.replace(oc.getChildNodes().get(2), new BooleanLiteralExpr(true));
                    } else {
                        oc.replace(oc.getChildNodes().get(2), new BooleanLiteralExpr(false));
                    }
                    newExpression = oc.clone();
                } else {
                    NodeList<Expression> argumentList = oc.getArguments();
                    argumentList.add(new BooleanLiteralExpr(true));
                    newExpression.setArguments(argumentList);
                }
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return newExpression;
    }
}
