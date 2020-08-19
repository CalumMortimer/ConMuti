package com.conmuti.conmuti.mutator.modifyparameter.esp;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which swaps the locks between synchronized statements nested inside other synchronized statements in a
 * compilation unit
 */
public class ESPModifier extends MutationModifier {
    /**
     * A method which walks through all the synchronized statements in the compilation unit.
     * If the synchronized statement has a parent synchronized statement with a different lock, the lock objects are
     * swapped between the synchronized statement
     *
     * The line reference and method name of the synchronized statement mutant is set in the
     * MutationModifier superclass.
     *
     * If the current synchronized statement is not marked for mutation, no action is performed and the mutationsVisited value of the
     * MutationModifier superclass is incremented.
     *
     * @param ss - the current synchronized statement
     * @param arg - always null
     * @return - the synchronized statement with any modifications applied
     */
    @Override
    public Node visit(SynchronizedStmt ss, Void arg) {
        super.visit(ss,arg);
        Node parentNode = ss.getParentNode().get();
        while (!(parentNode instanceof CompilationUnit)){
            if (parentNode instanceof SynchronizedStmt){
                if ((parentNode.getChildNodes().get(0)!=ss.getChildNodes().get(0)))
                {
                    if (getMutationNumber()==getMutationsVisited()){
                        Node insideLock = ss.getChildNodes().get(0).clone();
                        Node outsideLock = parentNode.getChildNodes().get(0).clone();
                        parentNode.replace(parentNode.getChildNodes().get(0),insideLock);
                        ss.replace(ss.getChildNodes().get(0),outsideLock);
                        setMutantLineReference(getNodeLineRef(ss));
                        setMethodName(getMethodNameFromNode(ss));
                    }
                    setMutationsVisited(getMutationsVisited()+1);
                    break;
                }
            }
            parentNode = parentNode.getParentNode().get();
        }
        return ss;
    }
}
