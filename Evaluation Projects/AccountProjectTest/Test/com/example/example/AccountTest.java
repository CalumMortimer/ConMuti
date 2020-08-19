package com.example.example;

import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class AccountTest {

    /***
     * Attempt to deposit from an account using two threads
     * RSK causes under-depositing
     *
     * @throws InterruptedException
     */
    @Test
    public void depositRace() throws InterruptedException {
        Account ac = new Account("",0.0);
        class Incrementer extends Thread{
            Account ac;
            public Incrementer(Account ac){
                this.ac = ac;
            }
            @Override
            public void run(){
                for (int i=0;i<1000000;i++)
                    ac.depsite(1);
            }
        }
        Incrementer i1 = new Incrementer(ac);
        Incrementer i2 = new Incrementer(ac);
        i1.start();
        i2.start();
        sleep(1000);
        assertEquals(2000000.0,ac.amount,0.0);
    }

    /**
     * Attempt to withdraw from an account using two threads
     * RSK causes under-withdrawing
     *
     * @throws InterruptedException
     */
    @Test
    public void withdrawRace() throws InterruptedException {
        Account ac = new Account("",0.0);
        class Incrementer extends Thread{
            Account ac;
            public Incrementer(Account ac){
                this.ac = ac;
            }
            @Override
            public void run(){
                for (int i=0;i<1000000;i++)
                    ac.withdraw(1);
            }
        }
        Incrementer i1 = new Incrementer(ac);
        Incrementer i2 = new Incrementer(ac);
        i1.start();
        i2.start();
        sleep(1000);
        assertEquals(-2000000.0,ac.amount,0.0);
    }

    /**
     * Attempt to transfer from one account to another using two threads
     * RSK causes under-transferring
     *
     * @throws InterruptedException
     */
    @Test
    public void transferRace() throws InterruptedException {
        Account ac1 = new Account("",0.0);
        Account ac2 = new Account("",0.0);
        class Incrementer extends Thread{
            Account ac1;
            Account ac2;
            public Incrementer(Account ac1,Account ac2){
                this.ac1 = ac1;
                this.ac2 = ac2;
            }
            @Override
            public void run(){
                for (int i=0;i<1000000;i++)
                    ac1.transfer(ac2,1);
            }
        }
        Incrementer i1 = new Incrementer(ac1,ac2);
        Incrementer i2 = new Incrementer(ac1,ac2);
        i1.start();
        i2.start();
        sleep(1000);
        assertEquals(-2000000.0,ac1.amount,0.0);
    }


    /**
     * Attempt to pay from two accounts into a third account
     * to underfund account 3
     *
     * @throws InterruptedException
     */
    @Test
    public void transferRace2() throws InterruptedException {
        Account ac1 = new Account("",0.0);
        Account ac2 = new Account("",0.0);
        Account ac3 = new Account("",0.0);
        class Incrementer extends Thread{
            Account ac1;
            Account ac2;
            public Incrementer(Account ac1,Account ac2){
                this.ac1 = ac1;
                this.ac2 = ac2;
            }
            @Override
            public void run(){
                for (int i=0;i<1000000;i++)
                    ac1.transfer(ac2,1);
            }
        }
        Incrementer i1 = new Incrementer(ac1,ac3);
        Incrementer i2 = new Incrementer(ac2,ac3);
        i1.start();
        i2.start();
        sleep(1000);
        assertEquals(2000000.0,ac3.amount,0.0);
    }
}