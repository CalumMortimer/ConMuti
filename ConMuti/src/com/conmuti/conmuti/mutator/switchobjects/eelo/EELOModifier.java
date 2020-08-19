package com.conmuti.conmuti.mutator.switchobjects.eelo;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.ArrayList;

/**
 * A class which performs unique swaps of lock() method call expressions within a compilation unit
 */
public class EELOModifier extends MutationModifier {
    private ArrayList<MethodCallExpr> expressions;
    private ArrayList<String> objects;
    private ArrayList<Integer> lineNumbers;
    private ArrayList<String> methodNames;

    public EELOModifier(){
        expressions = new ArrayList<MethodCallExpr>();
        objects = new ArrayList<String>();
        lineNumbers = new ArrayList<Integer>();
        methodNames = new ArrayList<String>();
    }

    /**
     * A method which visits the entire compilation unit, loads an arrayList of MethodCallExpressions
     * with all lock() method call expressions and an arraylist of Strings with all lock objects
     * the lock() method call expressions are called upon. It also loads an arraylist of the
     * line numbers and the method names of the method call expressions for use in the data output
     * of the software.
     *
     * @param cu - the compilation unit
     * @param arg - always null
     */
    @Override
    public CompilationUnit visit(CompilationUnit cu, Void arg) {
        super.visit(cu, arg);
        LoadExpressions loader = new LoadExpressions(this);
        loader.visit(cu,arg);
        loadObjects();
        swap();
        UnloadExpressions unloader = new UnloadExpressions(this);
        unloader.visit(cu,null);
        return cu;
    }

    /**
     * A method to swap the next set of different objects lock method call expressions are acted
     * upon, and store the location of one of the moved lock objects
     */
    private void swap(){
        boolean breakFlag = false;
        for (int i=0;i<objects.size();i++){
            for (int j=i;j<objects.size();j++){
                if (j!=i){
                    if (!(objects.get(i).equals(objects.get(j)))){
                        if (getMutationNumber()==getMutationsVisited()){
                            setMutantLineReference(lineNumbers.get(i));
                            setMethodName(methodNames.get(i));
                            breakFlag = true;
                            Expression tempScope = expressions.get(i).getScope().get().clone();
                            expressions.get(i).setScope(expressions.get(j).getScope().get().clone());
                            expressions.get(j).setScope(tempScope);
                        }
                        setMutationsVisited(getMutationsVisited()+1);
                    }
                }
                if (breakFlag)
                    break;
            }
            if (breakFlag)
                break;
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
     * into the superclass expressions array. When the constructor is called all of the
     * arraylists in the outer class are reset.
     */
    public class LoadExpressions extends MutationCounter {
        private EELOModifier outer;

        public LoadExpressions(EELOModifier outer){
            this.outer = outer;
            outer.expressions = new ArrayList<MethodCallExpr>();
            outer.lineNumbers = new ArrayList<Integer>();
            outer.methodNames = new ArrayList<String>();
            outer.objects = new ArrayList<String>();
        }

        /**
         * A method to visit each method call expression in the compilation unit
         * If the method call expression is a lock() method call expression then the
         * expression is stored in the outer class methodcallexpression arraylist
         *
         * The line numbers and the parent methods of the method call expressions are also
         * stored in separate lists.
         *
         * @param mc - the method call expression
         * @param arg - always null
         */
        @Override
        public void visit(MethodCallExpr mc,Void arg){
            super.visit(mc,arg);
            if (mc.getName().asString().equals("lock")) {
                outer.expressions.add(mc.clone());
                outer.methodNames.add(getMethodNameFromNode(mc));
                outer.lineNumbers.add(getNodeLineRef(mc));
            }
        }
    }

    /**
     * A class to unload the arraylist of method call expressions back in to the compilation unit.
     */
    public class UnloadExpressions extends MutationModifier {
        private EELOModifier outer;
        private int counter;

        public UnloadExpressions(EELOModifier outer){
            this.outer = outer;
            this.counter = 0;
        }

        /**
         * A method to visit each method call expression in the compilation unit
         * If the method call expression is a lock() method call expression the expression
         * is replaced with whichever expression is stored in the outer class expression
         * store
         *
         * @param mc - the method call expression
         * @param arg - always null
         */
        @Override
        public MethodCallExpr visit(MethodCallExpr mc,Void arg){
            super.visit(mc,arg);
            if (mc.getName().asString().equals("lock")) {
                mc = outer.expressions.get(counter).clone();
                counter++;
            }
            return mc;
        }
    }
}