package com.conmuti.unittests.mutator.modifyparameter.msp;

import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MSPSystemOutCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        Object o = new Object();\n" +
                "        synchronized(o){\n" +
                "        }\n" +
                "    }\n" +
                "}");
        MSPSystemOutCounter msp = new MSPSystemOutCounter();
        msp.visit(cu,null);
        assertEquals(1,msp.getMutantCount());
    }
}