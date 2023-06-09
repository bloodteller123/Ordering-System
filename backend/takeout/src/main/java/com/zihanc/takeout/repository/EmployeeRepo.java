package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
//    @Query(value = "SELECT * FROM employee_table where employee_table.username = :username LIMIT 1", nativeQuery=true)
    Optional<Employee> findByUsername(String username);

    //I guess the reason why 'findPageByUserName' fails at compilation is that 'UserName' is converted to 'userName'
    // which doesn't exist in Employee fields, while 'Username' => 'username' exists.
//    Page<Employee> findPageByUsername(String username, Pageable page);
    Optional<Page<Employee>> findPageByName(String name, Pageable page);
}