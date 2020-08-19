package com.conmuti.conmuti.mutator.switchobjects.rxo.core;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.ArrayList;

/**
 * A class which walks through a compilation unit and counts the different concurrency mechanism objects instantiated
 * and how many method calls are on each mechanism - the number of mutations stored is (mechanisms - 1)*methodCalls
 */
public class RXOGenericCounter extends MutationCounter {
    private ArrayList<String> mechanismNames;
    private ArrayList<String> types;
    private int methodCalls;

    public RXOGenericCounter(ArrayList<String> types){
        super();
        this.mechanismNames = new ArrayList<String>();
        this.methodCalls = 0;
        this.types = types;
    }

    /**
     * A method which walks through the compilation unit and determines the mechanism names instantiated and how many
     * method call expressions on those objects exist. The number of mutations is stored as (mechanisms - 1)*methodCalls
     *
     * @param cu - the compilation unit
     * @param arg - void
     */
    @Override
    public void visit(CompilationUnit cu, Void arg){
        super.visit(cu,arg);
        MechanismCounter meCounter = new MechanismCounter(this);
        meCounter.visit(cu,null);
        MethodCallCounter mcCounter = new MethodCallCounter(this);
        mcCounter.visit(cu,null);

        setMutantCount(methodCalls*(mechanismNames.size()-1));
    }

    /**
     * An inner class which visits VariableDeclarators in
     * a compilation unit and stores the different names of concurrency mechanisms
     * it finds in the outer class' mechanism names list
     */
    static class MechanismCounter extends MutationCounter{
        RXOGenericCounter outer;

        public MechanismCounter(RXOGenericCounter outer){
            this.outer = outer;
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
     * and stores the number of method calls relating to the concurrency mechanism name in the outer class' methodCalls parameter
     */
    static class MethodCallCounter extends MutationCounter{
        private RXOGenericCounter outer;

        public MethodCallCounter(RXOGenericCounter outer){
            super();
            this.outer = outer;
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
                    else if (mc.getScope().get() instanceof NameExpr){
                        if (((NameExpr) mc.getScope().get()).getName().asString().equals(mechanism)) {
                            outer.methodCalls++;
                        }
                    }
                }
            }
        }
    }
}