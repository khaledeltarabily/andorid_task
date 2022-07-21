package com.example.myapplicationtest.Views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationtest.Controllers.DepartmentController;
import com.example.myapplicationtest.R;

import androidx.appcompat.app.AppCompatActivity;

public class CreateDepartment extends AppCompatActivity {
    private EditText departmentName;
    DepartmentController departmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_department);

        departmentController = new DepartmentController(CreateDepartment.this);

        departmentName= (EditText) findViewById(R.id.departmentName);
        Button addDepartment = (Button) findViewById(R.id.addDepartmentID);


        addDepartment.setOnClickListener(v -> {
            String departmentNameValue = departmentName.getText().toString().trim();

            if (departmentNameValue.isEmpty()) {
                Toast.makeText(CreateDepartment.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }
           boolean isSaved = departmentController.insertDepartment(departmentNameValue);

            if (isSaved) {
                Toast.makeText(CreateDepartment.this, "Department has been added.", Toast.LENGTH_SHORT).show();
                departmentName.setText("");
            } else {
                Toast.makeText(CreateDepartment.this, "SomeThing Wrong Happen.", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
