package com.conmuti.conmuti.mutator.modifycriticalregion.skcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.Movers;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to reduce the critical region of a synchronized statement by one statement
 * above and below the synchronized statement
 */
public class SKCRModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the statement is marked as the current statement for mutation
     * the scope of the synchronicity is reduced to eliminate one statement above
     * and below the critical region and move them to the non-critical section.
     * The line reference and method name of the mutant is set in the
     * MutationModifier superclass.
     *
     * If the current synchronized statement is not marked for mutation,
     * no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     * @return - the synchronized statement with any modifications applied
     */
    @Override
    public SynchronizedStmt visit(SynchronizedStmt ss,Void arg){
        //super.visit(ss,arg);
        if (getMutationNumber()>=getMutationsVisited()) {
            if (ss.getChildNodes().get(1).getChildNodes().size()>=3){
                if (getMutationNumber() == getMutationsVisited()) {

                    setMethodName(getMethodNameFromNode(ss));
                    setMutantLineReference(getNodeLineRef(ss));

                    Movers.moveLastOutside(ss);
                    Movers.moveFirstOutside(ss);
                }
                setMutationsVisited(getMutationsVisited() + 1);
            }
        }
        return ss;
    }
}
