package com.conmuti.conmuti.mutator.modifycriticalregion.spcr;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.Movers;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class to split the critical region of a synchronized statement
 */
public class SPCRModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the statement is marked as the current statement for mutation,
     * a split within the critical region is inserted after the first synchronized statement child
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
            if (ss.getChildNodes().get(1).getChildNodes().size()>=2){
                if (getMutationNumber() == getMutationsVisited()) {

                    setMethodName(getMethodNameFromNode(ss));
                    setMutantLineReference(getNodeLineRef(ss));

                    Movers.moveFirstOutside(ss);

                    int ssLocation = getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);

                    Movers.synchronize(ss.getParentNode().get().getChildNodes().get(ssLocation-1),ss.getExpression());
                }
                setMutationsVisited(getMutationsVisited() + 1);
            }
        }
        return ss;
    }
}
