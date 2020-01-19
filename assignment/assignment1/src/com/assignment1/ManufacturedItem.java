package com.assignment1;

class ManufacturedItem extends Item{

    double tax = 0.395,finalPrice = 0.0;

    ManufacturedItem(String name,String type,int qty,double price){

        super(name,type,qty,price);
        finalPrice = qty * (price * (1 + this.tax));

    }

    @Override
    public String toString(){
        return "name : " + name + " type : " + type + " quantity : " + qty + " price : " + price + " tax : " + tax + " finalPrice : " + finalPrice;
    }
}
