package com.conmuti.conmuti.main;

import com.conmuti.conmuti.datalogger.DataLog;
import com.conmuti.conmuti.mutator.coreclasses.MutationWorker;
import com.conmuti.conmuti.mutator.modifycriticalregion.excr.EXCRCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.excr.EXCRModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRDownCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRDownModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRUpCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.shcr.SHCRUpModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.skcr.SKCRCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.skcr.SKCRModifier;
import com.conmuti.conmuti.mutator.modifycriticalregion.spcr.SPCRCounter;
import com.conmuti.conmuti.mutator.modifycriticalregion.spcr.SPCRModifier;
import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.astk.ASTKModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rfu.RFUCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rfu.RFUModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rsb.RSBCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rsb.RSBModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rsk.RSKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rsk.RSKModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rstk.RSTKModifier;
import com.conmuti.conmuti.mutator.modifykeyword.rvk.RVKCounter;
import com.conmuti.conmuti.mutator.modifykeyword.rvk.RVKModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPALockModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPASemaphoreCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.elpa.ELPASemaphoreModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rcxc.RCXCModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rjs.RJSModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.rna.RNACounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rna.RNAModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.rtxc.RTXCCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.rtxc.RTXCModifier;
import com.conmuti.conmuti.mutator.modifyoccurrence.san.SANCounter;
import com.conmuti.conmuti.mutator.modifyoccurrence.san.SANModifier;
import com.conmuti.conmuti.mutator.modifyparameter.esp.ESPCounter;
import com.conmuti.conmuti.mutator.modifyparameter.esp.ESPModifier;
import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRCounter;
import com.conmuti.conmuti.mutator.modifyparameter.mbr.MBRModifier;
import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msf.MSFModifier;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPSystemOutModifier;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPThisCounter;
import com.conmuti.conmuti.mutator.modifyparameter.msp.MSPThisModifier;
import com.conmuti.conmuti.mutator.modifyparameter.mxc.*;
import com.conmuti.conmuti.mutator.modifyparameter.mxt.MD2TModifier;
import com.conmuti.conmuti.mutator.modifyparameter.mxt.MX2TModifier;
import com.conmuti.conmuti.mutator.modifyparameter.mxt.MXTCounter;
import com.conmuti.conmuti.mutator.switchobjects.eelo.EELOCounter;
import com.conmuti.conmuti.mutator.switchobjects.eelo.EELOModifier;
import com.conmuti.conmuti.mutator.switchobjects.rxo.*;
import com.conmuti.conmuti.testrunner.DirectoryTestRunner;
import com.github.javaparser.JavaParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


/**
 * The main implementation of the ConMuti program
 */
public class Start {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
            //step 1: delete the log file if it already exists from a previous program run
            deleteLogFile();

            //step 2: obtain user data
            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            System.out.println("WELCOME TO CONMUTI");
            System.out.println("");
            System.out.println("Please enter a valid java file path to mutate...");
            System.out.println("For example, the file name: ");
            System.out.println("C:\\Users\\calum\\IdeaProjects\\MyProject\\src\\com\\example\\example\\Main.java");
            System.out.println("will mutate the file Main.java within package com.example.example within MyProject");
            System.out.println("All files within com.example.example will be compiled for testing");
            System.out.println("");
            System.out.println("Awaiting user input...");
            System.out.println("");
            String javaFileName = scanner.next();
            System.out.println("");
            System.out.println("Thanks!");
            System.out.println("");
            System.out.println("Please enter the valid class path to your compiled .class file...");
            System.out.println("For example, the file name: ");
            System.out.println("C:\\Users\\calum\\IdeaProjects\\MyProject\\out\\production\\com\\example\\example\\Main.class");
            System.out.println("will target the file Main.class within package com.example.example within MyProject");
            System.out.println("All compiled files within com.example.example will be moved to this directory for testing");
            System.out.println("");
            System.out.println("Awaiting user input...");
            System.out.println("");
            String classFileName = scanner.next();
            System.out.println("");
            System.out.println("Thanks!");
            System.out.println("");
            System.out.println("Please enter the valid directory path to the compiled unit tests you would like to use...");
            System.out.println("For example, the path name: ");
            System.out.println("C:\\Users\\calum\\IdeaProjects\\MyProject\\out\\production\\tests");
            System.out.println("will target all tests within the target directory");
            System.out.println("");
            System.out.println("Awaiting user input...");
            System.out.println("");
            String unitTestDirectory = scanner.next();
            System.out.println("Processing...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            UserData userData = new UserData(javaFileName, classFileName, unitTestDirectory);

            //Step 3: start up the JUnit Test Runner
            DirectoryTestRunner directoryTestRunner = new DirectoryTestRunner(userData);

            //Step 4: save the original source code
            Files.copy(Paths.get(userData.getJavaFileName()), Paths.get("SavedCode.java"), REPLACE_EXISTING);

            //Step 5: run the mutation tests
            JavaParser.getStaticConfiguration().setAttributeComments(false);
            MutationWorker.run(userData, directoryTestRunner, new MXTCounter(), new MX2TModifier(), "MXT (double time value)");
            MutationWorker.run(userData, directoryTestRunner, new MXTCounter(), new MD2TModifier(), "MXT (half time value)");
            MutationWorker.run(userData, directoryTestRunner, new MSPThisCounter(), new MSPThisModifier(), "MSP (sync on 'this')");
            MutationWorker.run(userData, directoryTestRunner, new MSPSystemOutCounter(), new MSPSystemOutModifier(), "MSP (sync on System.out)");
            MutationWorker.run(userData, directoryTestRunner, new ESPCounter(), new ESPModifier(), "ESP");
            MutationWorker.run(userData, directoryTestRunner, new MSFCounter(), new MSFModifier(), "MSF");
            MutationWorker.run(userData, directoryTestRunner, new MXCConstructorCounter(), new MXCConstructorDecModifier(), "MXC (decrement constructor)");
            MutationWorker.run(userData, directoryTestRunner, new MXCConstructorCounter(), new MXCConstructorIncModifier(), "MXC (increment constructor)");
            MutationWorker.run(userData, directoryTestRunner, new MXCMethodCounter(), new MXCMethodIncModifier(), "MXC (increment method)");
            MutationWorker.run(userData, directoryTestRunner, new MXCMethodCounter(), new MXCMethodDecModifier(), "MXC (decrement method)");
            MutationWorker.run(userData, directoryTestRunner, new MBRCounter(), new MBRModifier(), "MBR");
            System.out.println("Modified Parameters Tests Complete");
            MutationWorker.run(userData, directoryTestRunner, new RTXCCounter(), new RTXCModifier(), "RTXC");
            MutationWorker.run(userData, directoryTestRunner, new RCXCCounter(), new RCXCModifier(), "RCXC");
            MutationWorker.run(userData, directoryTestRunner, new RNACounter(), new RNAModifier(), "RNA");
            MutationWorker.run(userData, directoryTestRunner, new RJSCounter(), new RJSModifier(), "RJS");
            MutationWorker.run(userData, directoryTestRunner, new ELPALockCounter(), new ELPALockModifier(), "ELPA (lock)");
            MutationWorker.run(userData, directoryTestRunner, new ELPASemaphoreCounter(), new ELPASemaphoreModifier(), "ELPA (semaphore)");
            MutationWorker.run(userData, directoryTestRunner, new SANCounter(), new SANModifier(), "SAN");
            System.out.println("Modified Occurrence Tests Complete");
            MutationWorker.run(userData, directoryTestRunner, new ASTKCounter(), new ASTKModifier(), "ASTK");
            MutationWorker.run(userData, directoryTestRunner, new RSTKCounter(), new RSTKModifier(), "RSTK");
            MutationWorker.run(userData, directoryTestRunner, new RSKCounter(), new RSKModifier(), "RSK");
            MutationWorker.run(userData, directoryTestRunner, new RSBCounter(), new RSBModifier(), "RSB");
            MutationWorker.run(userData, directoryTestRunner, new RVKCounter(), new RVKModifier(), "RVK");
            MutationWorker.run(userData, directoryTestRunner, new RFUCounter(), new RFUModifier(), "RFU");
            System.out.println("Modified Keyword Tests Complete");
            MutationWorker.run(userData, directoryTestRunner, new RXOLockCounter(), new RXOLockModifier(), "RXO (lock)");
            MutationWorker.run(userData, directoryTestRunner, new RXOBarrierCounter(), new RXOBarrierModifier(), "RXO (barrier)");
            MutationWorker.run(userData, directoryTestRunner, new RXOLatchCounter(), new RXOLatchModifier(), "RXO (latch)");
            MutationWorker.run(userData, directoryTestRunner, new RXOSemaphoreCounter(), new RXOSemaphoreModifier(), "RXO (semaphore)");
            MutationWorker.run(userData, directoryTestRunner, new EELOCounter(), new EELOModifier(), "EELO");
            System.out.println("Switch Objects Tests Complete");
            MutationWorker.run(userData, directoryTestRunner, new SHCRUpCounter(), new SHCRUpModifier(), "SHCR (up)");
            MutationWorker.run(userData, directoryTestRunner, new SHCRDownCounter(), new SHCRDownModifier(), "SHCR (down)");
            MutationWorker.run(userData, directoryTestRunner, new SKCRCounter(), new SKCRModifier(), "SKCR");
            MutationWorker.run(userData, directoryTestRunner, new EXCRCounter(), new EXCRModifier(), "EXCR");
            MutationWorker.run(userData, directoryTestRunner, new SKCRCounter(), new SKCRModifier(), "SKCR");
            MutationWorker.run(userData, directoryTestRunner, new SPCRCounter(), new SPCRModifier(), "SPCR");
            System.out.println("Modify Critical Region Tests Complete");

            DataLog log = new DataLog();
            log.evaluateScore();

            //Step 6: (undoing the mods) replace the last mutated file with the original "SavedCode.java" file
            Files.move(Paths.get("SavedCode.java"), Paths.get(javaFileName), REPLACE_EXISTING);

            System.out.println("***************");
            System.out.println("***************");
            System.out.println("***************");
            System.out.println("MUTATION TESTING COMPLETE");
            System.out.println("The test report is located within the log.txt file stored within the root directory of this program");
            System.out.println("Thank you for using ConMuti");
            scanner.close();
    }

    public static void deleteLogFile() {
        File logger = new File("log.txt");
        if (logger.exists()) {
            boolean isSuccessful = logger.delete();
        }
    }
}
