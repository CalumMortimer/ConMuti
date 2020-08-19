package com.conmuti.conmuti.datalogger;

import java.io.*;

/**
 * A class to log output data from the mutation testing to a text file
 */
public class DataLog {
    private final int mutantStatus;
    private final String mutationOperator;
    private final String fileName;
    private final int lineNumber;
    private final String methodName;
    private static double deadMutants = 0.0;
    private static double livingMutants = 0.0;

    /**
     * Constructs the DataLog object
     */
    public DataLog(){
        this.mutantStatus = 0;
        this.mutationOperator = "";
        this.fileName = "";
        this.methodName = "";
        this.lineNumber = 0;
    }

    /**
     * Constructs the DataLog object
     *
     * @param mutantStatus - whether the mutant was killed or not (1 = dead, 0 = alive)
     * @param mutationOperator - a string representing the type of mutant (e.g. RSK for Remove Synchronized Keyword)
     * @param fileName - the name of the source code file under mutation testing
     * @param methodName - the name of the method which was mutated
     * @param lineNumber - the line number of the mutation
     */
    public DataLog(int mutantStatus,String mutationOperator,String fileName,String methodName,int lineNumber){
        this.mutantStatus = mutantStatus;
        this.mutationOperator = mutationOperator;
        this.fileName = fileName;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    /**
     * A method to evaluate the score of the mutation tests when all mutations are complete
     * (stored in log.txt file)
     *
     * @throws FileNotFoundException
     */
    public void evaluateScore() throws FileNotFoundException {
        File log = new File("log.txt");

        try{
            boolean fileCreated = log.createNewFile();
        }
        catch(IOException e){
            System.out.println("Failed I/O operation in com.conmuti.conmuti.datalogger.DataLog.evaluateScore");
        }

        PrintStream writeLog = new PrintStream(new FileOutputStream("log.txt",true));

        writeLog.println("");
        writeLog.println("");
        writeLog.println("Survived mutants found this program run: "+livingMutants);
        writeLog.println("Killed mutants found this program run: "+deadMutants);
        writeLog.println(" ");
        if ((deadMutants+livingMutants)!=0.0){
            //no divide by zero error
            writeLog.println("The total mutation score for the unit tests is "+((deadMutants)/(livingMutants+deadMutants)*100)+"%");
        }
        else{
            writeLog.println("Cannot evaluate the mutation score as no mutations were found!");
        }
    }

    /**
     * Formats the log entry line for output to the text file
     *
     * @return the line to be placed in the text file
     */
    private String formatLogEntry(){
        String fragment1;
        if (mutantStatus==1){
            fragment1 = "[X_X],dead mutant";
            deadMutants++;
        }
        else{
            fragment1 = "[O_O],LIVE mutant";
            livingMutants++;
        }
        String fragment2 = "TYPE: "+mutationOperator;
        String fragment3 = "FILE: "+fileName;
        String fragment4 = "METHOD: "+methodName;
        String fragment5 = "LINE: "+ lineNumber;
        return fragment1+","+fragment2+","+fragment3+","+fragment4+","+fragment5;
    }

    /**
     * Creates a "log.txt" file if the file does not exist, and appends the log entry to the file.
     * "log.txt" is erased during initial startup of ConMuti so that data is not appended to an old run of the program.
     *
     * @throws IOException - failed I/O operation when attempting to create the log file
     */
    public void logResult() throws IOException {
        File log = new File("log.txt");

        try{
            boolean fileCreated = log.createNewFile();
        }
        catch(IOException e){
            System.out.println("Failed I/O operation in com.conmuti.conmuti.datalogger.DataLog.logResult");
        }

        PrintStream writeLog = new PrintStream(new FileOutputStream("log.txt",true));
        writeLog.println(formatLogEntry());
    }
}
