package com.conmuti.conmuti.mutator.modifycriticalregion.excr;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.Movers;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to expand the critical region of a synchronized statement by one statement
 * above and below the synchronized statement
 */
public class EXCRModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the statement is marked as the current statement for mutation
     * the scope of the synchronicity is expanded to accommodate one statement
     * or expression below the synchronized statement and one statement or expression
     * above the synchronized statement.
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
        //check that there are statements around the synchronized statement
        if (getMutationNumber()>=getMutationsVisited()) {
            int location = getLocationOfChildNodeInParentList(ss.getParentNode().get(), ss);
            if (ss.getParentNode().get().getChildNodes().size() > (location + 1)) {
                if (location != 0) {
                    if (getMutationNumber() == getMutationsVisited()) {

                        setMethodName(getMethodNameFromNode(ss));
                        setMutantLineReference(getNodeLineRef(ss));

                        Movers.moveNextInside(ss);
                        Movers.movePreviousInside(ss);
                    }
                    setMutationsVisited(getMutationsVisited() + 1);
                }
            }
        }
        return ss;
    }
}
