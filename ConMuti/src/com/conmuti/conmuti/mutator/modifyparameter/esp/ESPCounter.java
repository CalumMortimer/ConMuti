package com.conmuti.conmuti.mutator.modifyparameter.esp;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.SynchronizedStmt;

/**
 * A class which walks through a compilation unit and counts the number of synchronized statements which are
 * child nodes of other synchronized statements using different locks
 */
public class ESPCounter extends MutationCounter {
    /**
     * A method which walks through the compilation unit and for every synchronized statement which is a child node
     * of another synchronized statement using a different lock, increments the mutantCount parameter
     *
     * @param ss - the current synchronized statement (synchronized block)
     * @param arg - void
     */
    @Override
    public void visit(SynchronizedStmt ss, Void arg){
        super.visit(ss,arg);
        Node parentNode = ss.getParentNode().get();
        while (!(parentNode instanceof CompilationUnit)){
            if (parentNode instanceof SynchronizedStmt){
                if ((parentNode.getChildNodes().get(0)!=ss.getChildNodes().get(0)))
                {
                    setMutantCount(getMutantCount()+1);
                    break;
                }
            }
            parentNode = parentNode.getParentNode().get();
        }
    }
}
