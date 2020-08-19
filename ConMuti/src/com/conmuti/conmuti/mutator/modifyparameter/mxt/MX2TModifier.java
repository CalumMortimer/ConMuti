package com.conmuti.conmuti.mutator.modifyparameter.mxt;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.expr.*;

/**
 * A class which multiplies the time value of wait, await, sleep and join method call expressions by 2
 */
public class MX2TModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in the compilation unit.
     * If the method call expression is "wait" or "await" or "join" or "sleep", and marked as the current field for mutation,
     * then the second child expression of the method call expression (the first argument of the method), if it exists, is doubled.
     *
     * The child expression becomes (childExpression)*2 following the mutation
     *
     * The line reference of the mutant and parent method name is set in the MutationModifier superclass.
     *
     * If the current method call expression is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param mc - the current method call expression
     * @param arg - always null
     * @return - the method call expression with any updates applied
     */
    @Override
    public MethodCallExpr visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);
        String exp = mc.getNameAsString();

        if(exp.equals("wait")||exp.equals("await")||exp.equals("join")||exp.equals("sleep")) {
            if (!mc.getArguments().isEmpty()) {
                if (getMutationNumber() == getMutationsVisited()) {

                    setMutantLineReference(getNodeLineRef(mc));
                    setMethodName(getMethodNameFromNode(mc));

                    Expression expressionToDouble = null;

                    expressionToDouble = (Expression) mc.getArguments().get(0);
                    expressionToDouble = new BinaryExpr(new EnclosedExpr(expressionToDouble.clone()), new IntegerLiteralExpr(2), BinaryExpr.Operator.MULTIPLY);

                    mc.getArguments().replace(mc.getArguments().get(0),expressionToDouble);
                }
                setMutationsVisited(getMutationsVisited() + 1);
            }
        }
        return mc;
    }
}



