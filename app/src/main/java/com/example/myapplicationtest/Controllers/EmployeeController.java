package com.example.myapplicationtest.Controllers;

import android.content.Context;

import com.example.myapplicationtest.Models.DepartmentModel;
import com.example.myapplicationtest.database.DBHandler;

import java.util.List;


public class EmployeeController {

    DBHandler dbHandler;

    public EmployeeController(Context context) {
        dbHandler = new DBHandler(context);
    }

    public boolean insertEmployee(String name, String hireDate, String email, int departmentId){
      return dbHandler.insertEmployee(name,hireDate,email,departmentId) > 0;
   }

    public List<DepartmentModel> getAllDepartment(){
        return dbHandler.getAllDepartment();
    }

}
