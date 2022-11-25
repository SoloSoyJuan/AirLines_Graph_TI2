package model;

import structures.IGraph;
import structures.IVertex;
import structures.ListGraph;
import structures.MatrixGraph;

import java.io.*;
import java.util.*;

public class Controller {
    private IGraph<Airport> airports;
    private List<Airport> names;

    public Controller(int graph){
        if(graph == 1){
            airports = new MatrixGraph<>(true, true, 50);
        }else{
            airports = new ListGraph<>(true, true);
        }
        this.names = new ArrayList<>();
    }

    public void loadData() throws IOException {
        File file = new File("src/docs/Data.txt");
        FileInputStream fileIn = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(in);

        String line = "";
        for (int i = 0; i < 50; i++) {
            line = bufferedReader.readLine();
            addNewAirport(line);
        }
        while((line = bufferedReader.readLine()) != null){
            String [] info = line.split(";");
            addConnection(info[0],info[1],Double.parseDouble(info[2]));
        }
    }

    public boolean addNewAirport(String name){
        boolean pass = true;
        Airport tnew = null;
        for (Airport a: names) {
            if(Objects.equals(a.getName(), name)){
                pass = false;
                break;
            }
        }
        if(pass){
            tnew = new Airport(name);
            names.add(tnew);
            airports.addVertex(tnew);
        }
        return pass;
    }

    public boolean addConnection(String name1, String name2, double time){
        boolean pass1 = false;
        boolean pass2 = false;
        Airport port1 = null;
        Airport port2 =null;
        for (Airport a: names) {
            if(Objects.equals(a.getName(), name1)){
                pass1 = true;
                port1 = a;
                break;
            }
        }
        for (Airport b: names) {
            if(Objects.equals(b.getName(), name2)){
                pass2 = true;
                port2 = b;
                break;
            }
        }
        if (pass1 && pass2){
            airports.addEdge(port1, port2, time);
            return pass1;
        }
        return false;
    }

    public String showGroups(){
        String info = "";
        airports.createdGroups();
        List<List<IVertex<Airport>>> groups = airports.getGroups();
        for (List<IVertex<Airport>> li: groups) {
            for (IVertex<Airport> a: li) {
                info += "--"+ a.getValue().getName();
            }
            info += "\n";
        }
        return info;
    }
}
