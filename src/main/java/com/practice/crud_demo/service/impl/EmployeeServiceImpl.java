package com.practice.crud_demo.service.impl;


import com.practice.crud_demo.dto.EmployeeDto;
import com.practice.crud_demo.entity.Employee;

import com.practice.crud_demo.exception.ResourceNotFoundException;
import com.practice.crud_demo.mapper.EmployeeMapper;
import com.practice.crud_demo.repository.EmployeeRepository;
import com.practice.crud_demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        log.info("Employee saved successfully :: "+savedEmployee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: "+id));
        log.info("Employee found successfully :: "+employee);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        log.info("Employees found successfully :: "+employees);
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
       Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: "+id));
       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());
       Employee updatedEmployeeObj=employeeRepository.save(employee);
       log.info("Employee updated successfully :: "+updatedEmployeeObj);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: "+id));
        employeeRepository.delete(employee);
        log.info("Employee deleted successfully :: "+employee);

    }
}
