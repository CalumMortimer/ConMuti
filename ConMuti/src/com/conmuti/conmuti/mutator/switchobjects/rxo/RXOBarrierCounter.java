package com.conmuti.conmuti.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.coreclasses.MutationCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.core.RXOGenericCounter;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;

/**
 * A class which walks calls on the RXOGenericCounter class with a set of object types relating to barriers
 */
public class RXOBarrierCounter extends MutationCounter {
    private int mutantCount;

    public int getMutantCount(){
        return mutantCount;
    }

    public void setMutantCount(int mutantCount) {
        this.mutantCount = mutantCount;
    }

    /**
     * A method which calls upon the RXOGenericCounter class with a set of object types
     * relating to barriers. The visit method of this class is an adapter to the visit method
     * of the RXOGenericCounter class.
     *
     * @param cu  - the compilation unit
     * @param arg - void
     */
    public void visit(CompilationUnit cu, Void arg) {
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add("CyclicBarrier");
        RXOGenericCounter counter = new RXOGenericCounter(typeList);
        counter.visit(cu, arg);
        setMutantCount(counter.getMutantCount());
    }
}
