package com.conmuti.conmuti.mutator.coreclasses;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import java.util.List;

/**
 * An abstract superclass for all mutation modifier classes
 */
public abstract class MutationModifier extends ModifierVisitor<Void> {
    private int mutationNumber;
    private int mutationsVisited;
    private int mutantLineReference;
    private String methodName;

    public MutationModifier(){
        super();
        this.mutationNumber = 0;
        this.mutationsVisited = 0;
        this.mutantLineReference = 0;
        this.methodName = "";
    }

    public int getMutationNumber() {
        return mutationNumber;
    }

    public int getMutationsVisited() {
        return mutationsVisited;
    }

    public int getMutantLineReference() {
        return mutantLineReference;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMutationNumber(int mutationNumber){this.mutationNumber=mutationNumber;}

    public void setMutationsVisited(int mutationsVisited) {
        this.mutationsVisited = mutationsVisited;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setMutantLineReference(int mutantLineReference) {
        this.mutantLineReference = mutantLineReference;
    }

    /**
     * A method to return the position of a child node in its parent node's node list
     *
     * @param parent - the parent node
     * @param child - the child node
     * @return - the position
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
     * A method which attempts to recover the method name of the method wrapping
     * around a node. It does this by moving upwards on the AST. If no method name
     * is found, null is returned.
     *
     * @param node - the node within the method
     * @return - the name of the method
     */
    public static String getMethodNameFromNode(Node node){
        while (!(node instanceof CallableDeclaration)) {
            node = node.getParentNode().get();
            if (node instanceof CompilationUnit){
                return null;
            }
        }
        CallableDeclaration declaration = (CallableDeclaration) node;
        return declaration.getDeclarationAsString(false, false, false);
    }

    /**
     * A method to return the line number of any node within an AST
     *
     * @param node - the node
     * @return - the line number
     */
    public static int getNodeLineRef(Node node){
        return node.getBegin().get().line;
    }

}
