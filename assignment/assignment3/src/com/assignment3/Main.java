package com.assignment3;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int choice=1;
        HashMap<String,Node> graph=new HashMap<>();
        DependencyGraph dg= new DependencyGraph(graph);

        while(true){
            System.out.println("enter 1 to add node,2 to add dependency, 3 to delete dependency, 4 to delete node");

            System.out.println("5 to get immediate children, 6 to get immediate parents, 7 to get ancestors ,8 to get descendents , 0 to exit");

            choice=(Integer.parseInt(sc.nextLine()));
            if(choice==0){
                break;
            }else if(choice==1){

                System.out.println("enter id");
                String id=sc.nextLine();
                System.out.println("enter name");
                String name=sc.nextLine();
                dg.addNode(id,name);

            }else if(choice==2){

                System.out.println("enter parent id and child id");
                dg.addDependency(sc.nextLine(),sc.nextLine());

            }
            else if(choice==3){

                System.out.println("enter parent id and child id");
                dg.deleteDependency(sc.nextLine(),sc.nextLine());

            }
            else if(choice==4){

                System.out.println("enter id");
                dg.deleteNode(sc.nextLine());

            }else if(choice==5){

                System.out.println("enter id");
                HashSet<String> children=dg.immediateChildren(sc.nextLine());
                for(String ids:children){
                    System.out.println(dg.graph.get(ids).toString());
                }

            }else if(choice==6){

                System.out.println("enter id");
                HashSet<String> parents=dg.immediateParents(sc.nextLine());
                for(String ids:parents){
                    System.out.println(dg.graph.get(ids).toString());
                }

            }else if(choice==7){

                System.out.println("enter id");
                HashSet<String> ancestors=dg.ancestors(sc.nextLine());
                for(String ids:ancestors){
                    System.out.println(dg.graph.get(ids).toString());
                }

            }else if(choice==8){

                System.out.println("enter id");
                HashSet<String> descendents=dg.descendents(sc.nextLine());
                for(String ids:descendents){
                    System.out.println(dg.graph.get(ids).toString());
                }

            }else{

                System.out.println("wrong choice! enter again");
                choice=Integer.parseInt(sc.nextLine());
            }
        }
    }
}
