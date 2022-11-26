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
                            "(3) Show the best fly for you\n"+
                            "(4) Add a new Airport\n"+
                            "(5) Add a new connection\n"+
                            "(0) Exit \n");
        option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    public static void actions(int option){
        String name;
        String name2;
        switch (option){
            case 1:
                System.out.println("\nGroups of Airports:\n"+con.showGroups());
                break;
            case 2:
                System.out.println("Type the Name of the Airport\n");
                name = sc.nextLine();
                System.out.println(con.showOneGroup(name));
                break;
            case 3:
                System.out.println("Type the Name of the departure flight");
                name = sc.nextLine();
                System.out.println("Type the Name of the arrival flight");
                name2 = sc.nextLine();
                System.out.println("The best fly is:\n"+con.createTheFly(name,name2));
                break;
            case 4:
                System.out.println("Type the Name of the new City: example (Denver,Colorado)\n");
                name = sc.nextLine();
                if(con.addNewAirport(name)){
                    System.out.println("Airport added successfully\n");
                }else{
                    System.out.println("Airport all ready exist\n");
                }
                break;
            case 5:
                System.out.println("Type the Name of the departure flight");
                name = sc.nextLine();
                System.out.println("Type the Name of the arrival flight");
                name2 = sc.nextLine();
                System.out.println("Type the minutes of the fly");
                double time = sc.nextInt();
                sc.nextLine();
                if(con.addConnection(name, name2, time)){
                    System.out.println("Connection added successfully\n");
                }else{
                    System.out.println("One of the Airports does not exist\n");
                }
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
