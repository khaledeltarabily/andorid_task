package com.example.myapplicationtest.Models;

public class DepartmentModel {

    private int id;
    private String name;

    public DepartmentModel() {}

    public DepartmentModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name; // What to display in the Spinner list.
    }
}
