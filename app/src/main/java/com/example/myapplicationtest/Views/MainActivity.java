package com.example.myapplicationtest.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Views.EmployeesAndDepartmentsView.EmployeesAndDepartment;
import com.example.myapplicationtest.Views.Users.activity.UsersActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addEmployee = (Button) findViewById(R.id.idBtnAddEmployee);
        Button addDepartment = (Button) findViewById(R.id.addDepartment);
        Button employeesAndDepartments = (Button) findViewById(R.id.idBtnViewEmployeesAndDepartments);
        Button showAllUsers = (Button) findViewById(R.id.showUsers);

        addEmployee.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateEmployee.class);
            MainActivity.this.startActivity(intent);
        });

        addDepartment.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateDepartment.class);
            MainActivity.this.startActivity(intent);
        });

        employeesAndDepartments.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EmployeesAndDepartment.class);
            MainActivity.this.startActivity(intent);
        });

        showAllUsers.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UsersActivity.class);
            MainActivity.this.startActivity(intent);
        });
    }

}