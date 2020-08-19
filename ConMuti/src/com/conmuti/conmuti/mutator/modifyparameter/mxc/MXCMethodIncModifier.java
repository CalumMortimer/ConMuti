package com.conmuti.conmuti.mutator.modifyparameter.mxc;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.*;

/**
 * A class which increments the values of permits in semaphore method calls in a compilation unit
 */
public class MXCMethodIncModifier extends MutationModifier {
    /**
     * A method which adds "+1" to the first argument of a method call expression
     *
     * @param mc - the method call expression
     * @return - the updated method call
     */
    private MethodCallExpr addPlusOneToMethodCallFirstArgument(MethodCallExpr mc){
        Expression oldCount = (Expression) mc.getChildNodes().get(2).clone();
        BinaryExpr newCount = new BinaryExpr(new EnclosedExpr(oldCount), new IntegerLiteralExpr(1), BinaryExpr.Operator.PLUS);
        mc.replace(mc.getChildNodes().get(2), newCount);
        return mc;
    }

    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression is "acquire", "acquireUninterruptibly", "release", "tryAcquire",
     * and marked as the current concurrency method call for mutation
     * its number of permits is incremented by one
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
                if ((name.substring(0, 7).equals("acquire")) || (name.equals("release"))) {
                    if (mc.getChildNodes().size() == 3) {
                        mc = addPlusOneToMethodCallFirstArgument(mc);
                    } else {
                        mc = provideMethodCallWithTwoPermits(mc);
                    }
                } else {
                    //tryAcquire(int permits) or tryAcquire(int permits, long timeout, TimeUnit unit) is used
                    if ((mc.getChildNodes().size() == 3) || (mc.getChildNodes().size() == 5)) {
                        mc = addPlusOneToMethodCallFirstArgument(mc);
                    }
                    //tryAcquire() is used
                    if (mc.getChildNodes().size() == 2) {
                        mc = provideMethodCallWithTwoPermits(mc);
                    }
                    //tryAcquire(long timeout,TimeUnit unit) is used (replace with tryAcquire(2,timeout,unit))
                    if (mc.getChildNodes().size() == 4) {
                        NodeList<Expression> newArguments = new NodeList<Expression>();
                        newArguments.add(new IntegerLiteralExpr(2));
                        newArguments.add((Expression) mc.getChildNodes().get(2).clone());
                        newArguments.add((Expression) mc.getChildNodes().get(3).clone());
                        if (mc.getScope().isPresent()) {
                            mc = new MethodCallExpr(mc.getScope().get(), mc.getName(), newArguments);
                        } else {
                            mc = new MethodCallExpr(null, mc.getName(), newArguments);
                        }
                    }
                }
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return mc;
    }

    /**
     * A method which returns a method call with two permits instead of the default method call
     *
     * @param mc - the default method call to be modified
     * @return - the updated method call
     */
    private MethodCallExpr provideMethodCallWithTwoPermits(MethodCallExpr mc){
        NodeList<Expression> newExpressionArg = new NodeList<Expression>();
        newExpressionArg.add(new IntegerLiteralExpr(2));
        MethodCallExpr newExpression = null;
        if (mc.getScope().isPresent()) {
            newExpression = new MethodCallExpr(mc.getScope().get(), mc.getName(), newExpressionArg);
        } else {
            newExpression = new MethodCallExpr(null, mc.getName(), newExpressionArg);
        }
        mc = newExpression;
        return mc;
    }

}