package com.practice.crud_demo.controller;


import com.practice.crud_demo.dto.EmployeeDto;
import com.practice.crud_demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        log.info("Employee saved successfully :: "+savedEmployee);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
       EmployeeDto employeeDto= employeeService.getEmployeeById(id);
       log.info("Employee found successfully :: "+employeeDto);
       return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllEmployees(){
        List<EmployeeDto> employees= employeeService.getAllEmployees();
        log.info("Employees found successfully :: "+employees);
        return ResponseEntity.ok(employees);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto updatedEmployeeDto= employeeService.updateEmployee(id,updatedEmployee);
        log.info("Employee updated successfully :: "+updatedEmployeeDto);
        return ResponseEntity.ok(updatedEmployeeDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        log.info("Employee deleted successfully :: "+id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
