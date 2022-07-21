package com.example.myapplicationtest.Views.EmployeesAndDepartmentsView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplicationtest.Models.EmployeeModel;
import com.example.myapplicationtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeesAndDepartmentsAdapter extends RecyclerView.Adapter<EmployeesAndDepartmentsAdapter.ViewHolder> {


    private List<EmployeeModel> employees;

    public EmployeesAndDepartmentsAdapter(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeesAndDepartmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_employee_and_department, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EmployeesAndDepartmentsAdapter.ViewHolder holder, int position) {
        EmployeeModel employee = employees.get(position);

        TextView name = holder.employeeNameTextView;
        TextView hireDate = holder.employeeHireDateTextView;
        TextView email = holder.employeeEmailTextView;
        TextView departmentName = holder.employeeDepartmentNameTextView;
        name.setText(employee.getName());
        hireDate.setText(employee.getHireDate());
        email.setText(employee.getEmail());
        departmentName.setText(employee.getDepartment().getName());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }



public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView employeeNameTextView;
    public TextView employeeHireDateTextView;
    public TextView employeeEmailTextView;
    public TextView employeeDepartmentNameTextView;



    public ViewHolder(View itemView) {
        super(itemView);
        employeeNameTextView = (TextView) itemView.findViewById(R.id.employeeName);
        employeeHireDateTextView = (TextView) itemView.findViewById(R.id.employeeHireDate);
        employeeEmailTextView = (TextView) itemView.findViewById(R.id.employeeEmail);
        employeeDepartmentNameTextView = (TextView) itemView.findViewById(R.id.employeeDepartmentName);
    }
}
}