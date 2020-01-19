package com.assignment1;

class Item{

    String name,type;
    int qty;
    double price;

    Item(String name,String type,int qty,double price){

        this.name = name;
        this.type = type;
        this.qty = qty;
        this.price = price;

    }

    @Override
    public String toString(){
        return "name : " + name + " type : " + type + " quantity : " + qty + " price : " + price;
    }
}