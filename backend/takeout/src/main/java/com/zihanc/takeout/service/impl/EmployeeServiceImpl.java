package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.Employee;
import com.zihanc.takeout.repository.EmployeeRepo;
import com.zihanc.takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee findByUsername(String userName) {
        if(userName==null) return null;
        return employeeRepo.findByUsername(userName).orElse(null);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        if(id==null) return Optional.empty();
        return employeeRepo.findById(id);
    }

    @Override
    public void saveUser(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public Page<Employee> findPageByName(String name, Pageable paging) {
        return employeeRepo.findPageByName(name, paging).orElse(null);
    }
    @Override
    public Page<Employee> findAll(Pageable paging){
        return employeeRepo.findAll(paging);
    }
}
