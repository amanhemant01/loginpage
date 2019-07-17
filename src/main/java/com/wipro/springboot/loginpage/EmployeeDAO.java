package com.wipro.springboot.loginpage;

import org.springframework.stereotype.Repository;



@Repository
public class EmployeeDAO
{
    private static Employees list = new Employees();
     
    static
    {
        list.getEmployeeList().add(new Employee(1, "Hemant", "Tiwari", "amanhemant01@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Aman", "Tiwari", "ashokhemant123@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "Jon", "Snow", "trueking@gmail.com"));
    }
     
    public Employees getAllEmployees()
    {
        return list;
    }
     
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
}