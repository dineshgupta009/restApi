package com.example.Service;

import com.example.Model.Employee;
import com.example.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static List<Employee> list = new ArrayList<>();


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getSingleEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Employee Not Found With Id");
    }

    @Override
    public void deleteEmployeeById(Long id) {

         if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
            }
            else {
             throw new RuntimeException(
                      "Employee Not Found With Id");
         }
//    static {
//        Employee e =new Employee();
//        e.setName("Dinesh");
//        e.setAge(32);
//        e.setEmail("Dinesh@gmail.com");
//        e.setDepartment("IT");
//        e.setLocation("Nagpur");
//
//        e.setName("Dinesh1");
//        e.setAge(33);
//        e.setEmail("Dinesh1@gmail.com");
//        e.setDepartment("IT1");
//        e.setLocation("Nagpur1");
//
//        list.add(e);
//    }
    }

//    @DeleteMapping("/employees/{id}")
//    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}