package com.assignment2;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.lang.reflect.Field;

class SortedUser implements Comparator<String> , Serializable{
    @Override
    public int compare(String s1 , String s2){
        return s1.compareTo(s2);
    }
}


class Data implements Serializable{

    private static final long serialversionUID = 123456789L;
    TreeMap<String,User> users = new TreeMap<String,User>(new SortedUser());

}

public class Main{
    public static void main(String[] args){

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Data data = null;

        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("userData.txt")));
            data = (Data)in.readObject();
            in.close();
        }catch (Exception ex) {
            if(data == null) data = new Data();
        }

        while(choice != 5){

            System.out.println("1 -> add user details");
            System.out.println("2 -> display user details");
            System.out.println("3 -> delete user details");
            System.out.println("4 -> save user details");
            System.out.println("5 -> exit");
            choice = Integer.parseInt(sc.nextLine());

            if(choice == 1){

                try{
                    System.out.println("enter name");
                    String name = sc.nextLine();

                    System.out.println("enter address");
                    String address = sc.nextLine();

                    System.out.println("enter age");
                    int age = sc.nextInt();

                    System.out.println("enter rollno");
                    int rollno = sc.nextInt();
                    sc.nextLine();

                    System.out.println("enter courses(comma separated)");
                    String temp = sc.nextLine();

                    ArrayList<String> courses = new ArrayList<>();
                    courses.addAll(Arrays.asList(temp.split(",")));

                    if(courses.size() < 4){
                        System.out.println("enter more courses ,minimum 4 courses required!");
                        temp = sc.nextLine();
                        courses.addAll(Arrays.asList(temp.split(",")));
                    }

                    User u = new User(name,address,age,rollno,courses);

                    if(data.users.containsKey(name + "," + Integer.toString(rollno))){
                        System.out.println("student already exists! replace(y/n)");
                        if(sc.nextLine().equals("y")){
                            data.users.put(name + "," + Integer.toString(rollno),u);
                        }
                    }else{
                        data.users.put(name + "," + Integer.toString(rollno),u);
                    }

                }catch(Exception e){
                    System.out.println("cannot add student!");
                    sc.nextLine();
                }

            }else if(choice == 2){

                try{
                    System.out.println("enter criteria(name,address,rollno,age)");
                    String response = sc.nextLine();

                    TreeMap<String,User> allUsers = new TreeMap<>(new SortedUser());

                    for(User obj : data.users.values()){
                        try{
                            for(Field f : obj.getClass().getDeclaredFields()){
                                if(f.getName().equals(response)){
                                    allUsers.put(f.get(obj).toString(),obj);
                                    break;
                                }
                            }
                        }catch(IllegalAccessException ex){
                            ex.printStackTrace();
                        }
                    }

                    System.out.println("name  rollno  age  address  courses");
                    for(User u : allUsers.values()){
                        System.out.println(u.name + " " + u.rollno + " " + u.age + " " + u.address + " " + u.courses.toString());
                    }

                }catch(Exception e){
                    System.out.println("cannot display!");
                }
            }else if(choice == 3){
                try{
                    System.out.println("enter rollno");
                    int rollno = sc.nextInt();

                    String key = "";
                    for(String k : data.users.keySet()){
                        if(k.split(",")[1].equals(Integer.toString(rollno))){
                            key = k;
                            break;
                        }
                    }
                    if(key != "")data.users.remove(key);
                }catch(Exception e){
                    System.out.println("cannot delete user!");
                }

            }else if(choice == 4){
                try{
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("userData.txt")));
                    out.writeObject(data);
                    out.close();
                }catch(Exception e){
                    System.out.println("cannot save user details!");
                }
            }else if(choice == 5){
                break;
            }else{
                System.out.println("invalid input ,try again!");
                choice = 0;
            }
        }
    }
}
