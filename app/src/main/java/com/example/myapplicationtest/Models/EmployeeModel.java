package com.example.myapplicationtest.Models;

public class EmployeeModel {
    private int id;
    private String name;
    private String hireDate;
    private String email;
    private DepartmentModel departmentModel;

    public EmployeeModel(){}


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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartmentModel getDepartment() {
        return departmentModel;
    }

    public void setDepartment(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }
}
