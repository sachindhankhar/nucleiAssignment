package com.assignment4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Item> items=new ArrayList<>();

        Parallel pl = new Parallel(items);

        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pl.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pl.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
