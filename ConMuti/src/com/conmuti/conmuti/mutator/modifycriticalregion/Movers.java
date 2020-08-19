package com.conmuti.conmuti.mutator.modifycriticalregion;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing methods suitable for moving statements and expressions in and out
 * of synchronized statements
 */
public class Movers {
    /**
     * The addStatement method in the JavaParser NodeWithStatements interface is bugged
     * and does not preserve the order of nodes in child lists - this method fixes the
     * implementation for this application
     *
     * @param index - the location in the child list to insert a node
     * @param parent - the parent node
     * @param newChild - the child node to be added to the parent list
     */
    public static void addStatementFixed(int index, BlockStmt parent, Node newChild){
        ArrayList<Node> savedNodes = new ArrayList<Node>();
        while(parent.getChildNodes().size()>index){
            savedNodes.add(parent.getChildNodes().get(index));
            parent.remove(parent.getChildNodes().get(index));
        }
        if (newChild instanceof Statement)
            parent.addStatement((Statement) newChild);
        else
            parent.addStatement((Expression) newChild);
        for (Node node : savedNodes){
            if (node instanceof Statement)
                parent.addStatement((Statement) node);
            else
                parent.addStatement((Expression) node);
        }
    }

    /**
     * A method to return the index of a child node in a parent's node list
     *
     * @param parent - the parent node
     * @param child - the child node
     * @return - the position of the child node in the parent node node list
     */
    private static int getLocationOfChildNodeInParentList(Node parent, Node child){
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
     * A method to move the first statement or expression within a synchronized statement
     * outside of the critical region - i.e. immediately before the synchronized statement
     *
     * @param ss - the synchronized statement
     */
    public static void moveFirstOutside(SynchronizedStmt ss){
        int location = Movers.getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
        //bring the statement or expression outside the synchronized statement
        addStatementFixed(location,(BlockStmt) ss.getParentNode().get(),ss.getChildNodes().get(1).getChildNodes().get(0).clone());
        //remove the statement or expression from inside the statement
        ss.getChildNodes().get(1).remove(ss.getChildNodes().get(1).getChildNodes().get(0));
    }

    /**
     * A method to move the statement or expression after a synchronized statement
     * inside the critical region
     *
     * @param ss - the synchronized statement
     */
    public static void moveNextInside(SynchronizedStmt ss){
        int location = Movers.getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
        //bring the statement or expression inside the synchronized statement
        if (ss.getParentNode().get().getChildNodes().get(location+1) instanceof Statement)
            ((BlockStmt) ss.getChildNodes().get(1)).addStatement((Statement) ss.getParentNode().get().getChildNodes().get(location+1).clone());
        else
            ((BlockStmt) ss.getChildNodes().get(1)).addStatement((Expression) ss.getParentNode().get().getChildNodes().get(location+1).clone());
        //remove the statement or expression from outside the statement
        ss.getParentNode().get().remove(ss.getParentNode().get().getChildNodes().get(location+1));
    }

    /**
     * A method to move the statement before a synchronized statement into the first executed
     * statement of the synchronized statement
     *
     * @param ss - the synchronized statement
     */
    public static void movePreviousInside(SynchronizedStmt ss){
        int location = Movers.getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
        //bring the statement or expression into the synchronized statement
        addStatementFixed(0, (BlockStmt) ss.getChildNodes().get(1),ss.getParentNode().get().getChildNodes().get(location-1).clone());
        //remove the statement or expression from outside the statement
        ss.getParentNode().get().remove(ss.getParentNode().get().getChildNodes().get(location-1));
    }

    /**
     * A method to move the last statement or expression within a synchronized statement
     * outside of the critical region - i.e. immediately after the synchronized statement
     *
     * @param ss - the synchronized statement
     */
    public static void moveLastOutside(SynchronizedStmt ss){
        int location = Movers.getLocationOfChildNodeInParentList(ss.getParentNode().get(),ss);
        //bring the statement or expression outside the synchronized statement
        addStatementFixed(location+1,(BlockStmt) ss.getParentNode().get(),ss.getChildNodes().get(1).getChildNodes().get(ss.getChildNodes().get(1).getChildNodes().size()-1).clone());
        //remove the statement or expression from inside the statement
        ss.getChildNodes().get(1).remove(ss.getChildNodes().get(1).getChildNodes().get(ss.getChildNodes().get(1).getChildNodes().size()-1));
    }

    /**
     * A method to to synchronize the provided "statement" around the provided "lock"
     *
     * @param statement - the statement to be synchronized
     * @param lock - the locking object
     */
    public static void synchronize(Node statement,Node lock){
        Node statementParent = statement.getParentNode().get();

        statementParent.replace(statement,new SynchronizedStmt((Expression) lock,new BlockStmt(new NodeList<Statement>((Statement) statement))));
    }
}
