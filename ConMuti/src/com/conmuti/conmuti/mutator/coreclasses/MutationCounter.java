package com.conmuti.conmuti.mutator.coreclasses;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * An abstract superclass for all mutation counter classes
 */
public abstract class MutationCounter extends VoidVisitorAdapter<Void> {
    private int mutantCount;

    public MutationCounter(){
        this.mutantCount = 0;
    }

    /**
     * A method to return the position of a child node in a parent node
     * within the AST
     *
     * @param parent - the parent node
     * @param child - the child node
     * @return - the location of the child node in its parent node list
     */
    public int getLocationOfChildNodeInParentList(Node parent,Node child){
        int position = -1;
        List<Node> parentNodeList = parent.getChildNodes();
        for (int i=0;i<parentNodeList.size();i++){
            if (child == parentNodeList.get(i)){
                position = i;
                return position;
            }
        }
        return position;
    }

    /**
     * A method to return the number of potential mutants counted
     * by the mutation counter
     *
     * @return - the number of potential mutants
     */
    public int getMutantCount() {
        return mutantCount;
    }

    /**
     * A method to set the number of potential mutants
     *
     * @param mutantCount - the number of potential mutants
     */
    public void setMutantCount(int mutantCount) {
        this.mutantCount = mutantCount;
    }

}
