package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Employee findByUsername(String userName);
    Optional<Employee> findById(Long id);
    void saveUser(Employee employee);
    Page<Employee> findPageByName(String name, Pageable page);
    Page<Employee> findAll(Pageable paging);
}
