package com.example.example;

import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;


public class RaceConditionTest {
    @Test
    public void incrementTest() throws InterruptedException {
        RaceCondition rc = new RaceCondition();
        class Incrementer extends Thread{
            RaceCondition rc;
            public Incrementer(RaceCondition rc){
                this.rc = rc;
            }
            @Override
            public void run(){
                for (int i=0;i<1000000;i++)
                    rc.increment();
            }
        }
        Incrementer t1 = new Incrementer(rc);
        Incrementer t2 = new Incrementer(rc);
        t1.start();
        t2.start();
        //wait for threads to finish
        sleep(1000);
        assertEquals(2000000,rc.getCount());
    }
}