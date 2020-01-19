package com.assignment1;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


            ArrayList<Item> items = new ArrayList<>();

            String response = "y";
            while(response.equals("y")){
                try {
                    String type = "";
                    int qty = 0;
                    double price = 0.0;

                    System.out.println("enter item details( -name <name>, -price <price> , -quantity <qty> , -type <raw|manufactured|imported> )");
                    String name = sc.nextLine().split(" ")[1];

                    for (int i = 0; i < 3; ++i) {

                        String temp = sc.nextLine();

                        if (temp.split(" ")[0].substring(1).equals("type"))
                            type = temp.split(" ")[1];

                        else if (temp.split(" ")[0].substring(1).equals("quantity"))
                            qty = Integer.parseInt(temp.split(" ")[1]);

                        else if (temp.split(" ")[0].substring(1).equals("price"))
                            price = Double.parseDouble(temp.split(" ")[1]);

                        else {
                            System.out.println("invalid input ,try again!");
                            i--;
                        }
                    }

                    if (!type.equals("raw") && !type.equals("manufactured") && !type.equals("imported")) {

                        System.out.println("type of item should be specified!");
                        type = sc.nextLine().split(" ")[1];

                    }

                    Item item = null;
                    if (type.equals("raw")) {

                        item = new RawItem(name, type, qty, price);

                    } else if (type.equals("manufactured")) {

                        item = new ManufacturedItem(name, type, qty, price);

                    } else if (type.equals("imported")) {

                        item = new ImportedItem(name, type, qty, price);

                    }

                    items.add(item);
                }catch(Exception e){
                    System.out.println("cannot add item!");
                }

                System.out.println("do you want to enter more items(y/n)");
                response = sc.nextLine();
            }
            for(Item i : items){
                System.out.println(i);
            }

        sc.close();
    }
}

