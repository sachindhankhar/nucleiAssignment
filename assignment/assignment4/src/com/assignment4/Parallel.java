package com.assignment4;

import java.sql.*;
import java.util.ArrayList;

public class Parallel{

    String url="jdbc:mysql://localhost:3306/studentDB";
    String user="root";
    String password="root";
    Connection con;
    ArrayList<Item> items;

    Parallel(ArrayList<Item> items){

        try {
            con=DriverManager.getConnection(url,user,password);
            this.items=items;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

  void produce() throws InterruptedException{

          synchronized (this){
              try {

                  String query="select * from item";
                  PreparedStatement ps=con.prepareStatement(query);
                  ResultSet rs=ps.executeQuery();

                  String name="",type="";
                  int quantity=0;
                  double price=0.0,tax=0.0;

                  while(rs.next()){
                      name=rs.getString("name");
                      price=rs.getDouble("price");
                      type=rs.getString("type");
                      quantity=rs.getInt("quantity");
                      tax=rs.getDouble("tax");
                      items.add(new Item(name,type,quantity,price,tax));
                      if(items.size()==1){
                          notify();
                      }
                  }
                  ps.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }


  }

    void consume() throws InterruptedException{

            synchronized (this){
                try {
                    while(items.size()==0){
                        System.out.println("waiting!");
                        wait();
                    }
                    for(Item i:items){

                        double finalprice=i.quantity*i.price*(1+i.tax);

                        String query="update item set finalprice=? where price=? and type=?";
                        PreparedStatement ps=con.prepareStatement(query);

                        ps.setDouble(1,finalprice);
                        ps.setDouble(2,i.price);
                        ps.setString(3,i.type);

                        ps.execute();
                        ps.close();
                    }
                } catch (SQLException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



    }


