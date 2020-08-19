package com.conmuti.conmuti.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.*;

/**
 * A class which decrements the values of permits in semaphore method calls in a compilation unit
 */
public class MXCMethodDecModifier extends MutationModifier {
    /**
     * A method which adds "-1" to the first argument of a method call expression
     *
     * @param mc - the method call expression
     * @return - the updated method call
     */
    private MethodCallExpr addMinusOneToMethodCallFirstArgument(MethodCallExpr mc){
        Expression oldCount = (Expression) mc.getChildNodes().get(2).clone();
        BinaryExpr newCount = new BinaryExpr(new EnclosedExpr(oldCount), new IntegerLiteralExpr(1), BinaryExpr.Operator.MINUS);
        mc.replace(mc.getChildNodes().get(2), newCount);
        return mc;
    }

    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression is "acquire", "acquireUninterruptibly", "release", "tryAcquire",
     * and marked as the current concurrency method call for mutation
     * its number of permits is decremented by one
     *
     * The line reference and method name of the mutant is set in the
     * MutationModifier superclass.
     *
     * If the current method call expression is a semaphore method call expression regarding permits,
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
        if ((name.equals("acquire")) || (name.equals("acquireUninterruptibly")) || (name.equals("release")) || (name.equals("tryAcquire"))) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(mc));
                setMethodName(getMethodNameFromNode(mc));
                if ((mc.getChildNodes().size()==3)||(mc.getChildNodes().size()==5)){
                    mc = addMinusOneToMethodCallFirstArgument(mc);
                }
                else{
                    mc = null;
                }
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return mc;
    }
}