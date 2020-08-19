package com.conmuti.conmuti.mutator.modifyoccurrence.elpa;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;

/**
 * A class which replaces lock, lockInterruptibly and tryLock method call expressions with a different one of those
 * three method call expressions in a compilation unit
 */
public class ELPALockModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression name is lock, lockInterruptibly, or tryLock
     * and marked as the current concurrency object for mutation, the method call expression is changed to a different
     * type of those three lock method calls, which is based on whether the mutationNumber is odd or even
     *
     * The line reference and method name of the mutant is set in the
     * MutationModifier superclass.
     *
     * If the current expression is a method call expression with the correct name
     * but is not marked for mutation,
     * no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented by two.
     *
     * @param mc  - the current method call expression
     * @param arg - always null
     * @return - the method call expression with any modifications applied
     */
    @Override
    public Node visit(MethodCallExpr mc, Void arg) {
        super.visit(mc, arg);
        //if the target mutation number is odd, visit potential odd mutants
        //otherwise, visit the potential even mutants
        if ((getMutationNumber()%2==1)&&(getMutationsVisited()==0)){
            setMutationsVisited(1);
        }
        String name = mc.getName().asString();
        if ((name.equals("lock"))||(name.equals("lockInterruptibly"))||(name.equals("tryLock"))) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(mc));
                setMethodName(getMethodNameFromNode(mc));
                if ((name.equals("lock"))&&(getMutationNumber()%2==1)){
                    mc.setName(new SimpleName("lockInterruptibly"));
                }
                else if ((name.equals("lock"))&&(getMutationNumber()%2==0)){
                    mc.setName(new SimpleName("tryLock"));
                }
                else if ((name.equals("lockInterruptibly"))&&(getMutationNumber()%2==1)){
                    mc.setName(new SimpleName("lock"));
                }
                else if ((name.equals("lockInterruptibly"))&&(getMutationNumber()%2==0)){
                    mc.setName(new SimpleName("tryLock"));
                }
                else if ((name.equals("tryLock"))&&(getMutationNumber()%2==1)){
                    mc.setName(new SimpleName("lock"));
                }
                else if ((name.equals("tryLock"))&&(getMutationNumber()%2==0)){
                    mc.setName(new SimpleName("lockInterruptibly"));
                }
            }
            setMutationsVisited(getMutationsVisited() + 2);
        }
        return mc;
    }
}