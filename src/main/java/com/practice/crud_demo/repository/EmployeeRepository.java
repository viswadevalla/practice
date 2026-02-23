package com.practice.crud_demo.repository;

import com.practice.crud_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //Optional<Employee> findByEmail(String email);
}
