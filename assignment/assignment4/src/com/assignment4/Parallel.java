package com.assignment4;

import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Parallel  implements Runnable{

    String url="jdbc:mysql://localhost:3306/studentDB";
    String user="root";
    String password="root";
    Connection con;
    CopyOnWriteArrayList<Item> items;

    Parallel(CopyOnWriteArrayList items){

        try {
            con=DriverManager.getConnection(url,user,password);
            this.items=items;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    synchronized boolean checkItem(){

        System.out.println("thread "+Thread.currentThread().getId());
        if(items.size()>0) return true;
        return false;

    }
    @Override
    public void run() {

        int threadId=(int) (Thread.currentThread().getId()%2);

        if(threadId==0){

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
                    checkItem();
                    items.add(new Item(name,type,quantity,price,tax));
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            try {
                while(!checkItem()){
                    Thread.sleep(1000);
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
            } catch (SQLException|InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
