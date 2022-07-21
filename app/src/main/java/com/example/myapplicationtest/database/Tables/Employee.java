package com.example.myapplicationtest.database.Tables;

public class Employee {

    public static final String EMPLOYEE_TABLE = "EMPLOYEE";

    public static final String ID_EMP = "id";
    public static final String Name_EMP = "name";
    public static final String Hire_Date_EMP = "hireDate";
    public static final String Email_EMP = "email";
    public static final String DEPARTMENT_ID_EMP = "department_id";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + EMPLOYEE_TABLE + " ("
                    + ID_EMP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Name_EMP + " NVARCHAR(50)  NOT NULL,"
                    + Hire_Date_EMP + " DATETIME  NOT NULL,"
                    + Email_EMP + " NVARCHAR(50)  NOT NULL,"
                    +DEPARTMENT_ID_EMP+ " INTEGER NOT NULL , FOREIGN KEY ("+ DEPARTMENT_ID_EMP +") REFERENCES "+Department.DEPARTMENT_TABLE+"("+Department.ID_DEP+"))";
}
