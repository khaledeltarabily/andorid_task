package com.example.myapplicationtest.Views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplicationtest.Controllers.DepartmentController;
import com.example.myapplicationtest.Controllers.EmployeeController;
import com.example.myapplicationtest.Models.DepartmentModel;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Validations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText employeeName, employeeHireDate, employeeEmail;
    private Spinner departments;
    private final Calendar calendar= Calendar.getInstance();
    EmployeeController employeeController;
    List<DepartmentModel> departmentModels;
    int departmentIdValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_emmployee);

        employeeController = new EmployeeController(CreateEmployee.this);

        Button addEmployee = (Button) findViewById(R.id.idBtnAddEmployee);
        employeeName = (EditText) findViewById(R.id.name);
        employeeHireDate = (EditText)findViewById(R.id.hireDate);
        employeeEmail = (EditText)findViewById(R.id.email);
        departments = (Spinner)findViewById(R.id.idSelectDepartment);
        departments.setOnItemSelectedListener(this);


        departmentModels = employeeController.getAllDepartment();

        ArrayAdapter<DepartmentModel> departmentAdapter = new ArrayAdapter<DepartmentModel>(CreateEmployee.this,
                R.layout.department_spinner, departmentModels);
        departmentAdapter.setDropDownViewResource(R.layout.department_spinner);
        departments.setAdapter(departmentAdapter);

        if(departments != null && !departmentModels.isEmpty()) {
            departmentIdValue = departmentModels.get(0).getId();
        }else {
            departmentModels = new ArrayList<DepartmentModel>();
            departmentModels.add(new DepartmentModel(0,"No Departments"));
        }

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };

        employeeHireDate.setOnClickListener(view -> new DatePickerDialog(CreateEmployee.this,
                date,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show());



        addEmployee.setOnClickListener(v -> {


            String employeeNameValue = employeeName.getText().toString().trim();
            String employeeDateValue = employeeHireDate.getText().toString().trim();
            String employeeEmailValue = employeeEmail.getText().toString().trim();

            if (employeeNameValue.isEmpty() && employeeDateValue.isEmpty() && employeeEmailValue.isEmpty()) {
                Toast.makeText(CreateEmployee.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Validations.isValidEmail(employeeEmailValue)) {
                Toast.makeText(CreateEmployee.this, "Please Correct Email", Toast.LENGTH_SHORT).show();
                return;
            }



            boolean isSaved= employeeController.insertEmployee(employeeNameValue, employeeDateValue, employeeEmailValue, departmentIdValue);


            if (isSaved) {
                Toast.makeText(CreateEmployee.this, "Employee has been added.", Toast.LENGTH_SHORT).show();
                employeeName.setText("");
                employeeHireDate.setText("");
                employeeEmail.setText("");
                departments.setSelection(0);
            } else {
                Toast.makeText(CreateEmployee.this, "SomeThing Wrong Happen.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateLabel(){
        String format="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(format, Locale.US);
        employeeHireDate.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        departmentIdValue = departmentModels.get(i).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
