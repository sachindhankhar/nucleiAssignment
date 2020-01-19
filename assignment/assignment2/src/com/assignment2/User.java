package com.assignment2;

import java.io.Serializable;
import java.util.ArrayList;

class User implements Serializable {

    String name , address;
    int age , rollno;
    ArrayList<String> courses;

    User(String name , String address , int age , int rollno , ArrayList<String> courses){
        this.name = name;
        this.address = address;
        this.age = age;
        this.rollno = rollno;
        this.courses = courses;
    }
}
