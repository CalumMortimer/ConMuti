package com.conmuti.conmuti.mutator.modifycriticalregion.shcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.Movers;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to shift the critical region of synchronized statements downwards by one statement
 */
public class SHCRDownModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the statement is marked as the current statement for mutation
     * the scope of the synchronicity is moved one step downwards.
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
        super.visit(ss,arg);
        //check that there are statements inside and below the synchronized statement
        if (getMutationNumber()>=getMutationsVisited()) {
            if (ss.getChildNodes().get(1).getChildNodes().size() > 0) {
                int location = getLocationOfChildNodeInParentList(ss.getParentNode().get(), ss);
                if (ss.getParentNode().get().getChildNodes().size() > (location + 1)) {
                    if (getMutationNumber() == getMutationsVisited()) {

                        setMethodName(getMethodNameFromNode(ss));
                        setMutantLineReference(getNodeLineRef(ss));

                        Movers.moveNextInside(ss);
                        Movers.moveFirstOutside(ss);
                    }
                    setMutationsVisited(getMutationsVisited() + 1);
                }
            }
        }
        return ss;
    }
}
