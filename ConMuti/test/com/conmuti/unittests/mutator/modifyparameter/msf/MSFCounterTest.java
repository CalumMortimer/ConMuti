package com.conmuti.unittests.mutator.modifyparameter.msf;

import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class MSFCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\n" +
                "\n" +
                "import java.util.concurrent.Semaphore;\n" +
                "\n" +
                "public class TestClass {\n" +
                "\n" +
                "    public void testMethod() throws InterruptedException {\n" +
                "        Semaphore sem1 = new Semaphore(1,false);\n" +
                "        Semaphore sem2 = new Semaphore(1,true);\n" +
                "        Semaphore sem3 = new Semaphore(1);\n" +
                "    }\n" +
                "}");
        MSFCounter msf = new MSFCounter();
        msf.visit(cu,null);
        assertEquals(3,msf.getMutantCount());
    }
}