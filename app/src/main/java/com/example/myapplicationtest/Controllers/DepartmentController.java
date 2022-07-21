package com.example.myapplicationtest.Controllers;

import android.content.Context;

import com.example.myapplicationtest.database.DBHandler;

public class DepartmentController {

    DBHandler dbHandler;

    public DepartmentController(Context context) {
        dbHandler = new DBHandler(context);
    }

    public boolean insertDepartment(String name){
        return dbHandler.insertDepartment(name) > 0;
    }

}
