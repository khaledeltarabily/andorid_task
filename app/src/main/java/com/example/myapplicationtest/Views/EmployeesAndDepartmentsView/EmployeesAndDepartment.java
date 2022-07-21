package com.example.myapplicationtest.Views.EmployeesAndDepartmentsView;

import android.os.Bundle;

import com.example.myapplicationtest.Controllers.EmployeesAndDepartmentController;
import com.example.myapplicationtest.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeesAndDepartment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employees_and_departments);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.employeesRecyclerView);

        EmployeesAndDepartmentController controller = new EmployeesAndDepartmentController(EmployeesAndDepartment.this);

        EmployeesAndDepartmentsAdapter adapter = new EmployeesAndDepartmentsAdapter(controller.getALLEmployees());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
