package com.lapremavera.androidatccourses;

public class DataModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public DataModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private int id;


}
