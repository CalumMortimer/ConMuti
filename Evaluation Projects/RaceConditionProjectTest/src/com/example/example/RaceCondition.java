package com.example.example;

public class RaceCondition {
    private int count;

    public RaceCondition(){
        count = 0;
    }

    public int getCount(){
        return count;
    }

    public synchronized void increment(){
        count++;
    }
}
