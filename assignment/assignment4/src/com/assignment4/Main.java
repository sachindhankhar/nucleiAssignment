package com.assignment4;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {

        CopyOnWriteArrayList<Item> items=new CopyOnWriteArrayList<>();

        for(int i=0;i<2;++i){
            Thread t= new Thread(new Parallel(items));
            t.start();
        }
    }
}
