package com.zihanc.takeout.controller;


import com.zihanc.takeout.model.Employee;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.result.PageResult;
import com.zihanc.takeout.service.EmployeeService;
import com.zihanc.takeout.utils.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // name of {username, password} should match the {u, p} in Employee class
    @PostMapping(value = "/login")
    public CommonResult login(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        Employee e = employeeService.findByUsername(employee.getUsername());

        if(e == null) return CommonResult.error("User not exists");
        // get encrypted password
        String passw = PasswordUtil.encryptPassword(employee.getPassword(), e.getSalt());
        if(!passw.equals(e.getPassword())){
            return CommonResult.error("password not matches");
        }
        if(e.getStatus() == 0){
            return CommonResult.error("user forbidden");
        }

        request.getSession().setAttribute("employee", e.getId());
        return CommonResult.success();
    }
    @PostMapping(value = "/logout")
    public CommonResult<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return CommonResult.success("logout successfully");
    }

    @PostMapping(value = "/signup")
    //signup admin
    public CommonResult signup(@RequestBody Employee record){
        log.info(record.toString());
        Employee e = employeeService.findByUsername(record.getUsername());
        if(e!=null) return CommonResult.error("User exists");
        PasswordUtil.encrypt(record);
        record.setStatus(1);
        employeeService.saveUser(record);
        return CommonResult.success("add new admin");
    }

    @PostMapping(path = "/")
    //signup regular user default pwd = 123456
    public CommonResult signupRegular(@RequestBody Employee record){
        log.info(record.toString());
        Employee e = employeeService.findByUsername(record.getUsername());
        if(e!=null) return CommonResult.error("User exists");
        // init password
        record.setPassword("123456");
        PasswordUtil.encrypt(record);
//        record.setCreateTime(LocalDateTime.now());
//        record.setUpdateTime(LocalDateTime.now());
        employeeService.saveUser(record);
        return CommonResult.success("add new user");
    }

    @GetMapping(value = "/page")
    // page index starts at 0;
    public CommonResult getEmployeesByUserName(
            String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PageResult response = null;
        // filed name or column name? for now lets use field name
        Pageable paging = PageRequest.of(page, size, Sort.by("createTime"));
        Page<Employee> results;
        if(name!=null) {
            results = employeeService.findPageByName(name, paging);
            if(results==null)  return CommonResult.error(null);
            log.info(results.getContent().toString());
        }else{
            results = employeeService.findAll(paging);
        }
        response = new PageResult(results.getTotalPages(), results.getTotalElements(), results.getContent());
        //TODO: maybe use dto?
        return CommonResult.success(response);
    }
    @PutMapping(value = "/update")
    public CommonResult update(@RequestBody Employee employee){
        log.info(employee.toString());
        Employee e = employeeService.findById(employee.getId()).orElse(null);
        if(e==null) return CommonResult.error("Something went wrong");
//        e.setUpdateTime(LocalDateTime.now());
//        https://stackoverflow.com/a/53717024/13062745
        employeeService.saveUser(e);
        return CommonResult.success();
    }

    @GetMapping(value = "/{id}")
    public CommonResult getEmployeeById(@PathVariable Long id){
        Employee e = employeeService.findById(id).orElse(null);
        if(e==null) return CommonResult.error("Something went wrong");
        return CommonResult.success(e);
    }
}

