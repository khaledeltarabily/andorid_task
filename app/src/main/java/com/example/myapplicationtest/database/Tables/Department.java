package com.example.myapplicationtest.database.Tables;

public class Department {

    public static final String DEPARTMENT_TABLE = "DEPARTMENT";

    public static final String ID_DEP = "id";
    public static final String Name_DEP = "name";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + DEPARTMENT_TABLE + " ("
                    + ID_DEP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Name_DEP + " NVARCHAR(50) )";
}
