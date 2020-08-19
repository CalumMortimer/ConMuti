package com.conmuti.conmuti.mutator.modifykeyword.rfu;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * A class which walks through a compilation unit and counts the number of occurrences of a finally block surrounding an unlock method call expression
 */
public class RFUCounter extends MutationCounter {
    /**
     * A method to walk through all the try statements in the compilation unit, and count the number of finally blocks containing unlock method call expressions.
     * The number of finally blocks surrounding unlock method call expressions is stored in the mutantCount parameter in the MutationCounter superclass.
     * Inner class FinallyContainsUnlockChecker walks through all of the method call expressions within each finally block to determine if any unlock method call declarations exist.
     *
     * @param ts - the current try statement
     * @param arg - always null
     */
    @Override
    public void visit(TryStmt ts, Void arg){
        super.visit(ts,arg);
        if (ts.getFinallyBlock().isPresent()) {
            BlockStmt bs = ts.getFinallyBlock().get();
            FinallyContainsUnlockChecker fcu = new FinallyContainsUnlockChecker();
            fcu.visit(bs, null);
            if (fcu.getFinallyContainsUnlock()) {
                setMutantCount(getMutantCount() + 1);
            }
        }
    }

    /**
     * A class which walks through a block statement and determines if at least one unlock method call expression exists.
     */
    class FinallyContainsUnlockChecker extends VoidVisitorAdapter<Void> {
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
            super.visit(mc,arg);
            if (mc.getNameAsString().equals("unlock")){
                setFinallyContainsUnlock(true);
            }
        }
    }
}
