package com.conmuti.unittests.datalogger;

import com.conmuti.conmuti.datalogger.DataLog;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DataLogTest {

    /**
     * Tests the logging of a couple of results by verifying the log is present at the end of the "log.txt" file
     */
    @Test
    public void logResult() {
        //log an alive mutant
        File logFile = new File("log.txt");
        if (logFile.exists()){
            boolean result = logFile.delete();
        }

        int mutantStatus = 0;
        String mutationOperator = "RSK";
        String fileName = "myFile.java";
        String methodName = "myMethod";
        int lineNumber = 12345;

        DataLog logEntry = new DataLog(mutantStatus,mutationOperator,fileName,methodName,lineNumber);
        try {
            logEntry.logResult();
        }
        catch(IOException e){
            Assert.fail();
        }

        File updatedLogFile = new File("log.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(updatedLogFile);
        }
        catch(FileNotFoundException FNF){
            Assert.fail();
        }
        String line = scanner.nextLine();
        assertEquals("[O_O],LIVE mutant,TYPE: RSK,FILE: myFile.java,METHOD: myMethod,LINE: 12345",line);

        //log a dead mutant
        File logFile2 = new File("log.txt");
        if (logFile2.exists()){
            boolean result = logFile2.delete();
        }

        int mutantStatus2 = 0;
        String mutationOperator2 = "RSK";
        String fileName2 = "myFile.java";
        String methodName2 = "myMethod2";
        int lineNumber2 = 12345;

        DataLog logEntry2 = new DataLog(mutantStatus2,mutationOperator2,fileName2,methodName2,lineNumber2);
        try {
            logEntry2.logResult();
        }
        catch(IOException e){
            Assert.fail();
        }

        File updatedLogFile2 = new File("log.txt");
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(updatedLogFile);
        }
        catch(FileNotFoundException FNF){
            Assert.fail();
        }
        String line2 = scanner2.nextLine();
        assertEquals("[O_O],LIVE mutant,TYPE: RSK,FILE: myFile.java,METHOD: myMethod,LINE: 12345",line2);
    }
}