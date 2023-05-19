package com.example.Service;

import com.example.Model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee saveEmployee(Employee employee);
    Optional<Employee> getSingleEmployee(Long id);

    void deleteEmployeeById(Long id);
}
