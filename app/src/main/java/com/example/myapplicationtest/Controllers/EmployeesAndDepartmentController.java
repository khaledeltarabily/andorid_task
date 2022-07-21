package com.example.myapplicationtest.Controllers;

import android.content.Context;

import com.example.myapplicationtest.Models.DepartmentModel;
import com.example.myapplicationtest.Models.EmployeeModel;
import com.example.myapplicationtest.database.DBHandler;

import java.util.List;

public class EmployeesAndDepartmentController {
    DBHandler dbHandler;

    public EmployeesAndDepartmentController(Context context) {
        dbHandler = new DBHandler(context);
    }

    public List<EmployeeModel> getALLEmployees(){
        return dbHandler.getAllEmployees();
    }

}
