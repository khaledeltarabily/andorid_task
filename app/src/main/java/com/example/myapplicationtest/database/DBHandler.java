package com.example.myapplicationtest.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplicationtest.Models.DepartmentModel;
import com.example.myapplicationtest.Models.EmployeeModel;
import com.example.myapplicationtest.database.Tables.Department;
import com.example.myapplicationtest.database.Tables.Employee;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "EMPLOYEE";

    private static final int DB_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Department.CREATE_TABLE);
        sqLiteDatabase.execSQL(Employee.CREATE_TABLE);
    }

    public long insertEmployee(String name, String hireDate, String email, int departmentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Employee.Name_EMP,name);
        values.put(Employee.Hire_Date_EMP, hireDate);
        values.put(Employee.Email_EMP, email);
        values.put(Employee.DEPARTMENT_ID_EMP, departmentId);

        long id = db.insert(Employee.EMPLOYEE_TABLE, null, values);

        db.close();
        return id;
    }

    public long insertDepartment(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Department.Name_DEP,name);

        long id = db.insert(Department.DEPARTMENT_TABLE, null, values);

        db.close();

        return id;
    }

    @SuppressLint("Range")
    public List<EmployeeModel> getAllEmployees(){
        List<EmployeeModel> employees = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Employee.EMPLOYEE_TABLE, new String[] {
                Employee.ID_EMP,
                Employee.Name_EMP,
                Employee.Hire_Date_EMP,
                Employee.Email_EMP,
                Employee.DEPARTMENT_ID_EMP},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(cursor.getInt(cursor.getColumnIndex(Employee.ID_EMP)));
                employee.setName(cursor.getString(cursor.getColumnIndex(Employee.Name_EMP)));
                employee.setHireDate(cursor.getString(cursor.getColumnIndex(Employee.Hire_Date_EMP)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(Employee.Email_EMP)));
                String DepartmentID =  cursor.getString(cursor.getColumnIndex(Employee.DEPARTMENT_ID_EMP));
                employee.setDepartment(getDepartmentByID(DepartmentID));
                employees.add(employee);
            } while (cursor.moveToNext());
        }

        return employees;
    }

    @SuppressLint("Range")
    public  List<DepartmentModel> getAllDepartment(){
        List<DepartmentModel> departments = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Department.DEPARTMENT_TABLE,
                new String[] {Department.ID_DEP,Department.Name_DEP}, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                DepartmentModel department = new DepartmentModel();
                department.setId(cursor.getInt(cursor.getColumnIndex(Department.ID_DEP)));
                department.setName(cursor.getString(cursor.getColumnIndex(Department.Name_DEP)));
                departments.add(department);
            } while (cursor.moveToNext());
        }
        return departments;
    }

    public DepartmentModel getDepartmentByID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Department.DEPARTMENT_TABLE,
                new String[]{Department.ID_DEP, Department.Name_DEP},
                Department.ID_DEP + "=?",
                new String[]{id}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare case object
        @SuppressLint("Range") DepartmentModel department = new DepartmentModel(
                cursor.getInt(cursor.getColumnIndex(Department.ID_DEP)),
                cursor.getString(cursor.getColumnIndex(Department.Name_DEP))
        );

        cursor.close();

        return department;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Employee.EMPLOYEE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Department.DEPARTMENT_TABLE);
        onCreate(sqLiteDatabase);
    }
}