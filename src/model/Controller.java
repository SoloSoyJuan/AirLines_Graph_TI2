package model;

import structures.IGraph;
import structures.ListGraph;
import structures.MatrixGraph;

public class Controller {
    IGraph<Airport> airports;

    public Controller(int graph){
        if(graph == 1){
            airports = new MatrixGraph<>(true, true, 50);
        }else{
            airports = new ListGraph<>(true, true);
        }
    }
}
