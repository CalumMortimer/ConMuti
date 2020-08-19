package com.conmuti.conmuti.mutator.switchobjects.rxo.core;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;

import java.util.ArrayList;

/**
 * A class which walks through a compilation unit and changes the concurrency mechanism object associated with a
 * concurrency mechanism method call
 */
public class RXOGenericModifier extends MutationModifier {
    private ArrayList<String> mechanismNames;
    private int methodCalls;
    private ArrayList<String> types;

    public RXOGenericModifier(ArrayList<String> types){
        super();
        this.mechanismNames = new ArrayList<String>();
        this.methodCalls = 0;
        this.types = types;
    }

    /**
     * A method which walks through all the method call expressions in the compilation unit.
     * If the method call expression is an expression on the set of concurrency mechanism object types passed to this
     * objects constructor, and marked as the current expression for mutation,
     * then the object the expression acts on is changed to a different object name of the same concurrency mechanism type
     *
     * The line reference of the mutant and parent method name is set in the MutationModifier superclass.
     *
     * If the current method call expression is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param mc - the current method call expression
     * @param arg - void
     */
    @Override
    public MethodCallExpr visit(MethodCallExpr mc, Void arg){
        super.visit(mc,arg);

        //populate the list of mechanism names and number of mechanism method calls
        if (getMutationsVisited()==0) {
            if (mc.findCompilationUnit().isPresent()) {
                MechanismCounter meCounter = new MechanismCounter(this);
                meCounter.visit(mc.findCompilationUnit().get(), null);
                MethodCallCounter mCounter = new MethodCallCounter(this);
                mCounter.visit(mc.findCompilationUnit().get(), null);
            }
        }

        //cycleNumber used to select which mechanism should replace each mechanism object in each method call
        int cycleNumber = getMutationNumber()/methodCalls;

        //all mechanism method calls need a scope defining the object name
        if (mc.getScope().isPresent()) {

            //get the mechanismName depending on which type of scope the method call expression has
            String mechanismName;
            if (mc.getScope().get() instanceof FieldAccessExpr) {
                mechanismName = ((FieldAccessExpr) mc.getScope().get()).getName().asString();
            } else {
                mechanismName = ((NameExpr) mc.getScope().get()).getName().asString();
            }

            //determine if the method call is a mechanism method call or something else
            for (String mechanism : mechanismNames) {
                if (mechanism.equals(mechanismName)) {
                    //if method call is a mechanism method call, change the mechanism
                    // to a unique mechanism if marked for mutation
                    //if not marked for mutation, then do nothing
                    if ((getMutationsVisited()+(cycleNumber*methodCalls)) == getMutationNumber()) {
                        setMethodName(getMethodNameFromNode(mc));
                        setMutantLineReference(getNodeLineRef(mc));
                        if (mechanismNames.get(cycleNumber).equals(mechanismName)) {
                            if (mc.getScope().get() instanceof FieldAccessExpr) {
                                ((FieldAccessExpr) mc.getScope().get()).setName(new SimpleName(mechanismNames.get(mechanismNames.size()-1)));
                            } else {
                                ((NameExpr) mc.getScope().get()).setName(new SimpleName(mechanismNames.get(mechanismNames.size()-1)));
                            }
                        } else {
                            if (mc.getScope().get() instanceof FieldAccessExpr) {
                                ((FieldAccessExpr) mc.getScope().get()).setName(new SimpleName(mechanismNames.get(cycleNumber)));
                            } else {
                                ((NameExpr) mc.getScope().get()).setName(new SimpleName(mechanismNames.get(cycleNumber)));
                            }
                        }
                    }
                    setMutationsVisited(getMutationsVisited() + 1);
                    break;
                }
            }
        }
        return mc;
    }

    /**
     * An inner class which visits VariableDeclarators in
     * a compilation unit and stores the different names of concurrency names
     * it finds in the outer class' mechanism names list
     */
    static class MechanismCounter extends MutationCounter{
        RXOGenericModifier outer;

        public MechanismCounter(RXOGenericModifier outer){
            super();
            this.outer = outer;
            outer.mechanismNames = new ArrayList<String>();
        }

        @Override
        public void visit(VariableDeclarator vd, Void arg){
            super.visit(vd,arg);
            boolean foundExistingMechanism = false;
            boolean foundType = false;
            String vdName = vd.getType().getElementType().asString();

            for (int i=0;i<outer.types.size();i++){
                if (outer.types.get(i).equals(vdName)){
                    foundType = true;
                    break;
                }
            }

            if (foundType){
                for (String mechanism : outer.mechanismNames){
                    if (mechanism.equals(vdName)){
                        foundExistingMechanism = true;
                        break;
                    }
                }
                if (!foundExistingMechanism) {
                    outer.mechanismNames.add(vd.getName().asString());
                }
            }
        }
    }

    /**
     * An inner class which walks through MethodCallExpressions in a compilation unit
     * and stores the number of method calls relating to the mechanisms in the outer class' methodCalls parameter
     */
    static class MethodCallCounter extends MutationCounter{
        private RXOGenericModifier outer;

        public MethodCallCounter(RXOGenericModifier outer){
            super();
            this.outer = outer;
            outer.methodCalls = 0;
        }

        @Override
        public void visit(MethodCallExpr mc, Void arg){
            super.visit(mc,arg);
            for (String mechanism : outer.mechanismNames) {
                if (mc.getScope().isPresent()) {
                    if (mc.getScope().get() instanceof FieldAccessExpr) {
                        if (((FieldAccessExpr) mc.getScope().get()).getName().asString().equals(mechanism)) {
                            outer.methodCalls++;
                        }
                    }
                    else{
                        if (((NameExpr) mc.getScope().get()).getName().asString().equals(mechanism)) {
                            outer.methodCalls++;
                        }
                    }
                }
            }
        }
    }
}