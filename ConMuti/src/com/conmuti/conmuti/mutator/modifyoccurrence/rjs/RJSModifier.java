package com.conmuti.conmuti.mutator.modifyoccurrence.rjs;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;

/**
 * A class which replaces join method call expressions with an appropriately timed sleep expression in a compilation unit
 */
public class RJSModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression name is join
     * and marked as the current concurrency object for mutation, the method call expression is changed to sleep with a time
     * equal to the optional timeout parameter, or 10 seconds
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
        //super.visit(mc, arg);
        String name = mc.getName().asString();
        if (name.equals("join")) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(mc));
                setMethodName(getMethodNameFromNode(mc));
                //join(int millis) is used
                if (mc.getChildNodes().size()==3){
                    NodeList<Expression> sleepTimes = new NodeList<Expression>();
                    sleepTimes.add((Expression) mc.getChildNodes().get(2).clone());
                    if (mc.getScope().isPresent()) {
                        mc = new MethodCallExpr(mc.getScope().get(), new SimpleName("sleep"), sleepTimes);
                    }
                    else{
                        mc = new MethodCallExpr(null,new SimpleName("sleep"),sleepTimes);
                    }
                }
                //join(int millis, int nanos) is used
                else if (mc.getChildNodes().size()==4){
                    NodeList<Expression> sleepTimes = new NodeList<Expression>();
                    sleepTimes.add((Expression) mc.getChildNodes().get(2).clone());
                    sleepTimes.add((Expression) mc.getChildNodes().get(3).clone());
                    if (mc.getScope().isPresent()) {
                        mc = new MethodCallExpr(mc.getScope().get(), new SimpleName("sleep"), sleepTimes);
                    }
                    else{
                        mc = new MethodCallExpr(null,new SimpleName("sleep"),sleepTimes);
                    }
                }
                //join() is used
                else{
                    NodeList<Expression> sleepTimes = new NodeList<Expression>();
                    sleepTimes.add(new IntegerLiteralExpr(10000));
                    if (mc.getScope().isPresent()){
                        mc = new MethodCallExpr(mc.getScope().get(),new SimpleName("sleep"),sleepTimes);
                    }
                    else{
                        mc = new MethodCallExpr(null, new SimpleName("sleep"),sleepTimes);
                    }
                }
            }
            setMutationsVisited(getMutationsVisited() + 1);
        }
        return mc;
    }
}