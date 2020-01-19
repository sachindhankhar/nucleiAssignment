package com.assignment1;

class RawItem extends Item{

    double tax = 0.125 , finalPrice = 0.0;

    RawItem(String name , String type , int qty , double price){

        super(name , type , qty , price);
        finalPrice = qty  * (price  * (1 + this.tax));

    }

    @Override
    public String toString(){
        return "name : " + name + " type : " + type + " quantity : " + qty + " price : " + price + " tax : " + tax + " finalPrice : " + finalPrice;
    }

}
