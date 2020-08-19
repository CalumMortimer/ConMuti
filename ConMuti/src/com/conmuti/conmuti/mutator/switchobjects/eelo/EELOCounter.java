package com.conmuti.conmuti.mutator.switchobjects.eelo;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.ArrayList;

/**
 * A class which counts every unique swap of lock() MethodCallExpressions on
 * objects within a compilation unit
 */
public class EELOCounter extends MutationCounter {
    private ArrayList<MethodCallExpr> expressions;
    private ArrayList<String> objects;

    public EELOCounter(){
        expressions = new ArrayList<MethodCallExpr>();
        objects = new ArrayList<String>();
    }

    /**
     * A method which visits the entire compilation unit, loads an arrayList of MethodCallExpressions
     * with all lock() method call expressions and an arraylist of Strings with all lock objects
     * the lock() method call expressions are called upon.
     *
     * The number of possible mutations is counted as the number of unique 2 element swaps which
     * can be made between elements of the array of string objects
     *
     * @param cu - the compilation unit
     * @param arg - always null
     */
    @Override
    public void visit(CompilationUnit cu, Void arg) {
        super.visit(cu, arg);
        LoadExpressions loader = new LoadExpressions(this);
        loader.visit(cu,arg);
        loadObjects();
        countMutations();
    }

    /**
     * A method to count the number of unique swaps between objects with a lock method call
     * The number is stored in the mutantCount parameter
     */
    private void countMutations(){
        for (int i=0;i<objects.size();i++){
            for (int j=i;j<objects.size();j++){
                if (j!=i){
                    if (!(objects.get(i).equals(objects.get(j)))){
                        setMutantCount(getMutantCount()+1);
                    }
                }
            }
        }
    }

    /**
     * A method to load the object string arrayList with the corresponding object names
     * from the MethodCallExpression arrayList
     */
    private void loadObjects(){
        for (MethodCallExpr method : expressions){
            if (method.getScope().isPresent()) {
                if (method.getScope().get() instanceof FieldAccessExpr){
                    objects.add(((FieldAccessExpr) method.getScope().get()).getName().asString());
                }
                else{
                    objects.add(((NameExpr) method.getScope().get()).getName().asString());
                }
            }
        }
    }

    /**
     * A class which unloads the method call expressions from the compilation unit
     * into the superclass expressions array
     */
    public class LoadExpressions extends MutationCounter {
        private EELOCounter outer;

        public LoadExpressions(EELOCounter outer){
            this.outer = outer;
        }

        /**
         * A method to visit each method call expression in the compilation unit
         * If the method call expression is a lock() method call expression then the
         * expression is stored in the outer class methodcallexpression arraylist
         *
         * @param mc - the method call expression
         * @param arg - always null
         */
        @Override
        public void visit(MethodCallExpr mc,Void arg){
            super.visit(mc,arg);
            if (mc.getName().asString().equals("lock")) {
                outer.expressions.add(mc.clone());
            }
        }
    }
}
