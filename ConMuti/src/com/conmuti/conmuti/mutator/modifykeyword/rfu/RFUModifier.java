package com.conmuti.conmuti.mutator.modifykeyword.rfu;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

/**
 * A class which removes the finally block around any unlock method call declaration in a compilation unit,
 * represented by the mutationNumber stored in the MutationModifier superclass
 */
public class RFUModifier extends MutationModifier {
    /**
     * A method which walks through all of the try statements in the compilation unit.
     * If the try statement has a finally block which contains an unlock method call expression, and it is marked
     * as the current try statement for mutation, the finally block is replaced with its child nodes -
     * i.e. finally{methodCallExpression1; methodCallExpression2;} becomes methodCallExpression1; methodCallExpression2;
     *
     * If mutated, the line reference of the finally block and its parent callable method name is set in the MutationModifier superclass.
     *
     * If the current try statement is not marked for mutation, no action is performed and the mutationsVisited value of the MutationModifier
     * superclass is incremented.
     *
     * Inner class FinallyContainsUnlockChecker walks through all of the method call expressions within each finally block to determine if any unlock method calls exist.
     *
     * The process for removing the finally block from the AST is as follows:
     *  1. In the parent node of the try block, save all of the nodes after the try/catch/finally expression
     *  2. Move all of the nodes from inside the finally block to directly after the try/catch/finally expression in the parent node
     *  3. Re-insert the saved nodes from the parent node so that they follow the finally nodes in the correct order
     *  4. Delete the entire finally block
     *
     * @param ts - the current try statement
     * @param arg - always null
     * @return - the try statement with any updates applied
     */
    @Override
    public TryStmt visit(TryStmt ts, Void arg){
        super.visit(ts,arg);

        //checking if there are unlock method calls within the finally section of this try statement
        BlockStmt bs = ts.getFinallyBlock().get();
        FinallyContainsUnlockChecker fcu = new FinallyContainsUnlockChecker();
        fcu.visit(bs,null);

        if (fcu.getFinallyContainsUnlock()) {
            //if this try statement is the one marked for modification
            if(getMutationNumber()==getMutationsVisited()) {
                setMethodName(getMethodNameFromNode(ts));
                setMutantLineReference(getNodeLineRef(ts));

                //the entire finally block of the try/catch/finally block - stored as a cloned block statement
                BlockStmt myBlockStmt = ts.getFinallyBlock().get().clone().asBlockStmt();
                //the parent node of the try/catch/finally block
                BlockStmt tsParent = (BlockStmt) ts.getParentNode().get();
                //the position of the try/catch block in the parent node
                int tryStmtPosition = getLocationOfChildNodeInParentList(tsParent, ts);

                //Step 1: save the nodes after the try/catch so that the unlocks can be reinserted in the parent node
                ArrayList<Node> savedNodes = new ArrayList<Node>();
                int tsParentChildNodeListLength = tsParent.getChildNodes().size();
                for (int i = tryStmtPosition + 1; i < tsParentChildNodeListLength; i++) {
                    savedNodes.add(tsParent.getChildNodes().get(i).clone());
                }
                for (int i = tryStmtPosition + 1; i < tsParentChildNodeListLength; i++) {
                    tsParent.remove(tsParent.getChildNodes().get(tryStmtPosition + 1));
                }

                //Step 2: add the block statement extracted from finally to the parent node
                myBlockStmt.setParentNode(tsParent);
                tsParent.addStatement(myBlockStmt);

                //Step 3: reinsert the preserved nodes from the ArrayList
                for (int i = 0; i < savedNodes.size(); i++) {
                    savedNodes.get(i).setParentNode(tsParent);
                    tsParent.addStatement((Statement) savedNodes.get(i));
                }

                //Step 4: remove the finally block
                ts.removeFinallyBlock();
            }
            setMutationsVisited(getMutationsVisited()+1);
        }
        return ts;
    }

    /**
     * A class which walks through a block statement and determines if at least one unlock method call expression exists.
     */
    static class FinallyContainsUnlockChecker extends VoidVisitorAdapter<Void> {
        private boolean finallyContainsUnlock;

        public FinallyContainsUnlockChecker(){
            this.finallyContainsUnlock = false;
        }

        public void setFinallyContainsUnlock(boolean finallyContainsUnlock) {
            this.finallyContainsUnlock = finallyContainsUnlock;
        }

        public boolean getFinallyContainsUnlock(){
            return this.finallyContainsUnlock;
        }

        /**
         * A method which walks through every method call expression within a block statement. If any method call expression
         * is the "unlock" method call expression, the finallyContainsUnlock boolean value is set to true.
         *
         * @param mc - the current method call expression
         * @param arg - always null
         */
        @Override
        public void visit(MethodCallExpr mc, Void arg){
            super.visit(mc,null);
            if (mc.getNameAsString().equals("unlock")){
                setFinallyContainsUnlock(true);
            }
        }
    }
}
