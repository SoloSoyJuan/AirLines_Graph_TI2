package model;

public class Airport {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Airport(String name){
        this.name = name;
    }
}
