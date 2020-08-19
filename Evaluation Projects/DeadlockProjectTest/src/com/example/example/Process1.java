package com.example.example;

class Process1 extends Thread {
    public Locks lock;

    public Process1(Locks lock){
        this.lock = lock;
    }

    public void run() {
        synchronized (lock.lock1) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock.lock2) {
            }
        }
    }
}
