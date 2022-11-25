package main;

import model.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Controller con;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("choose type of graph: \n"+
                            "(1) Matrix graph \n"+
                            "(2) List graph");
        int option = sc.nextInt();
        sc.nextLine();
        con = new Controller(option);
        con.loadData();
        do {
            option = mainMenu();
            actions(option);
        }while (option != 0);
    }

    public static int mainMenu(){
        int option = 0;
        System.out.println("******** Main Menu ********\n"+
                            "(1) Show the Groups of Airports\n"+
                            "(2) Show the Group of one Airport\n"+
                            "(3) \n"+
                            "(4) \n"+
                            "(0) Exit \n");
        option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    public static void actions(int option){
        switch (option){
            case 1:
                System.out.println(con.showGroups());
                break;
            case 2:
                System.out.println("Type the Name of the Airport\n");
                String name = sc.nextLine();
                System.out.println(con.showOneGroup(name));
                break;
            case 3:
                System.out.println("case 3");
                break;
            case 0:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("invalid option");
                break;
        }
    }
}
