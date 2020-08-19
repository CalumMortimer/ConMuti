package com.conmuti.unittests.mutator.modifykeyword.rfu;

import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rfu.RFUCounter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RFUCounterTest {

    @Test
    public void visit() {
        CompilationUnit cu = JavaParser.parse("package com.example.example;\r\n" +
                "\r\n" +
                "import java.util.concurrent.locks.Lock;\r\n" +
                "import java.util.concurrent.locks.ReentrantLock;\r\n" +
                "\r\n" +
                "public class TestClass {\r\n" +
                "    \r\n" +
                "    public void testMethod(){\r\n" +
                "        Lock l = new ReentrantLock();\r\n" +
                "        \r\n" +
                "        try{\r\n" +
                "            System.out.println(\"I tried\");\r\n" +
                "        }catch(Exception e){\r\n" +
                "            System.out.println(\"I failed\");\r\n" +
                "        }finally{\r\n" +
                "            l.unlock();\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}\r\n");
        RFUCounter rfu = new RFUCounter();
        rfu.visit(cu,null);
        assertEquals(1,rfu.getMutantCount());
    }
}