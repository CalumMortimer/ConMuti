package com.example.example;

import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class DeadlockTest {

    /**
     * Runs the main method of Deadlock on a second thread and
     * tests its execution time to check if a deadlock has occurred
     */
    @Test
    public void checkDeadlocking() throws InterruptedException {
        Locks lock = new Locks();
        Process1 p1 = new Process1(lock);
        Process2 p2 = new Process2(lock);
        p1.start();
        p2.start();
        sleep(1000);
        assertFalse(p1.isAlive());
    }
}