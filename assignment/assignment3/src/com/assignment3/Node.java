package com.assignment3;

import java.util.HashSet;

class Node{

    String id,name;
    HashSet<String>parents,children;

    Node(String id,String name,HashSet<String>parents,HashSet<String>children){

        this.id=id;
        this.name=name;
        this.parents=parents;
        this.children=children;

    }
    @Override
    public String toString(){
        return "Node id: "+id+" name: "+name+" parents: "+parents.toString()+" children: "+children.toString();
    }
}
