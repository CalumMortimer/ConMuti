package com.conmuti.conmuti.mutator.modifyoccurrence.san;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.ArrayList;

/**
 * A class which replaces getAndSet methods with a get method, then a set method in a compilation unit
 */
public class SANModifier extends MutationModifier {
    /**
     * A method which walks through all the method call expressions in a compilation unit
     * If the method call expression name is getAndSet
     * and marked as the current concurrency object for mutation, the method call expression is changed to get, and an
     * equivalent set method call expression is placed consecutively in the compilation unit
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
    public MethodCallExpr visit(MethodCallExpr mc, Void arg) {
        super.visit(mc, arg);
        String name = mc.getName().asString();
        MethodCallExpr mc2 = null;
        if (name.equals("getAndSet")) {
            if (getMutationNumber() == getMutationsVisited()) {
                setMutantLineReference(getNodeLineRef(mc));
                setMethodName(getMethodNameFromNode(mc));

                //get the parent block statement of the method call expression
                BlockStmt mcParent = (BlockStmt) mc.getParentNode().get().getParentNode().get();

                //get the position of the getAndSet expression within its parent node
                int getAndSetExprPosition = getLocationOfChildNodeInParentList(mcParent, mc.getParentNode().get());

                //save nodes after the getAndSet in parent node to they can be reinserted after the set
                //method call is inserted
                ArrayList<Node> savedNodes = new ArrayList<Node>();
                int mcParentChildNodeListLength = mcParent.getChildNodes().size();
                for (int i = getAndSetExprPosition + 1; i < mcParentChildNodeListLength; i++) {
                    savedNodes.add(mcParent.getChildNodes().get(i).clone());
                }
                for (int i = getAndSetExprPosition + 1; i < mcParentChildNodeListLength; i++) {
                    mcParent.remove(mcParent.getChildNodes().get(getAndSetExprPosition + 1));
                }

                //change getAndSet to get and add new set to parent
                mc.setName("get");
                if (mc.getScope().isPresent()){
                    mcParent.addStatement(new MethodCallExpr(mc.getScope().get(),new SimpleName("set"),mc.getArguments()));
                }
                else{
                    mcParent.addStatement(new MethodCallExpr(null,new SimpleName("set"),mc.getArguments()));
                }
                mc.setArguments(new NodeList<Expression>());

                //reinsert saved nodes into parent
                for (int i=0;i<savedNodes.size();i++){
                    savedNodes.get(i).setParentNode(mcParent);
                    mcParent.addStatement((Statement) savedNodes.get(i));
                }

            }
            setMutationsVisited(getMutationsVisited() + 1);
        }

        return mc;
    }
}