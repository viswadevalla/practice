package com.practice.crud_demo.mapper;

import com.practice.crud_demo.dto.EmployeeDto;
import com.practice.crud_demo.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        if (employee == null) {
            return null;
        }
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(), 
                employee.getLastName(), 
                employee.getEmail()
        );
    }
    
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        if (employeeDto == null) {
            return null;
        }
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}