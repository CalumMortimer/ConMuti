package com.conmuti.conmuti.mutator.switchobjects.rxo;

import com.conmuti.conmuti.mutator.coreclasses.MutationModifier;
import com.conmuti.conmuti.mutator.switchobjects.rxo.core.RXOGenericCounter;
import com.conmuti.conmuti.mutator.switchobjects.rxo.core.RXOGenericModifier;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;

/**
 * A class calls upon the RXOGenericModifier class with a set of object type names
 * relating to locks
 */
public class RXOLockModifier extends MutationModifier {

    private String methodName;
    private int mutantLineReference;

    public void setMutantLineReference(int mutantLineReference) {
        this.mutantLineReference = mutantLineReference;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getMutantLineReference() {
        return mutantLineReference;
    }
    /**
     * A class which calls upon the RXOGenericModifier class with a set of object type names relating
     * to locks. The visit method of this class is used as an adapter to the visit method
     * of the RXOGenericModifier class.
     *
     * @param cu - the compilation unit
     * @param arg - always null
     */
    public CompilationUnit visit(CompilationUnit cu, Void arg) {
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add("ReentrantLock");
        typeList.add("Lock");
        typeList.add("ReentrantReadWriteLock");
        typeList.add("ReentrantReadWriteLock.ReadLock");
        typeList.add("ReentrantReadWriteLock.WriteLock");
        RXOGenericModifier modifier = new RXOGenericModifier(typeList);
        modifier.visit(cu, arg);
        setMethodName(modifier.getMethodName());
        setMutantLineReference(modifier.getMutantLineReference());
        return cu;
    }
}
