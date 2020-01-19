package com.assignment3;

import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;

class DependencyGraph{

    HashMap<String,Node> graph;

    DependencyGraph(HashMap<String,Node> graph){

        this.graph=graph;

    }

    HashSet<String> immediateParents(String id){

        return graph.get(id).parents;

    }

    HashSet<String> immediateChildren(String id){

        return graph.get(id).children;

    }

    HashSet<String> ancestors(String id){

        HashSet<String> parent=immediateParents(id),ancestor=new HashSet<>();
        if(parent!=null){
            for(String ids:parent){
                ancestor.add(ids);
                ancestor.addAll(ancestors(ids));
            }
        }
        return ancestor;

    }

    HashSet<String> descendents(String id){

        HashSet<String> children=immediateChildren(id),descendent=new HashSet<>();
        if(children!=null){
            for(String ids:children){
                descendent.add(ids);
                descendent.addAll(descendents(ids));
            }
        }
        return descendent;

    }

    boolean deleteDependency(String parentId,String childId){

        try{
            graph.get(parentId).children.remove(childId);
            graph.get(childId).children.remove(parentId);
            return true;
        }catch(Exception e){
            System.out.println("cannot remove!");
            return false;
        }

    }

    boolean deleteNode(String id){

        try{
            for(String ids:graph.get(id).parents){
                graph.get(ids).children.remove(id);
            }
            for(String ids:graph.get(id).children){
                graph.get(ids).parents.remove(id);
            }
            return true;
        }catch(Exception e){
            System.out.println("cannot delete!");
            return false;
        }

    }

    boolean addDependency(String parentId,String childId){

        HashSet<String> allDescendents=descendents(childId);

        try{
            if(!allDescendents.contains(parentId)){
                graph.get(parentId).children.add(childId);
                graph.get(childId).parents.add(parentId);
                return true;
            }else{
                System.out.println("dependency "+parentId+" -> "+childId+" violates acyclic property");
                return false;
            }
        }catch(Exception e){
            System.out.println("error in adding dependency!");
            return false;
        }

    }

    boolean addNode(String id,String name){

        try{
            Scanner sc=new Scanner(System.in);
            if(graph.containsKey(id)){
                System.out.println("this id exists already! enter (y/n) to replace");
                if(sc.nextLine().equals("n")) return false;
            }

            graph.put(id,new Node(id,name,new HashSet<String>(),new HashSet<String>()));
            System.out.println("Enter parent id,otherwise enter n");
            String parent=sc.nextLine();

            while(!parent.equals("n")){
                addDependency(parent,id);
                System.out.println("Enter parent id,otherwise enter n");
                parent=sc.nextLine();
            }

            System.out.println("Enter child id,otherwise enter n");
            String child=sc.nextLine();
            while(!child.equals("n")){
                addDependency(child,id);
                System.out.println("Enter child id,otherwise enter n");
                child=sc.nextLine();
            }
            return true;
        }catch(Exception e){
            System.out.println("cannot add node!");
            return false;
        }

    }
}
