package com.example.Controller;

import com.example.Model.Employee;
import com.example.Repository.EmployeeRepository;
import com.example.Service.EmployeeService;
import com.example.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/employee")
    public List<Employee> getEmployee(){
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(employee);
    }


    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
                                                    @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeService.getSingleEmployee(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
//        employee.setId(employeeDetails.getId());
        employee.setLocation(employeeDetails.getLocation());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setAge(employeeDetails.getAge());
        final Employee updatedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/employee")
    public void deleteEmployee(@RequestParam long id){
          employeeService.deleteEmployeeById(id);
    }
}
