package com.assignment1;

class ImportedItem extends Item{

    double tax = 0.125,importDuty = 0.10,finalPrice = 0.0,surcharge = 0.0;

    ImportedItem(String name,String type,int qty,double price){

        super(name,type,qty,price);
        finalPrice = qty * (price * (1 + this.tax + this.importDuty));
        surcharge = (finalPrice <= 100) ? 5 : (finalPrice > 100 && finalPrice <= 200) ? 10 : 0.05 * finalPrice;
        finalPrice += surcharge;

    }

    @Override
    public String toString(){
        return "name : " + name + " type : " + type + " quantity : " + qty + " price : " + price + " tax : " + tax + " surcharge :" + surcharge + " import duty : " + importDuty + " finalPrice : " + finalPrice;
    }
}
