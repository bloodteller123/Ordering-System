package com.zihanc.takeout.controller;

import com.zihanc.takeout.model.User;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.service.UserService;
import com.zihanc.takeout.utils.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public CommonResult signup(@RequestBody User user){
        log.info(user.toString());

        try{
            User u = userService.save(user);
//            temp
            Storage.setId(u.getId());
            return CommonResult.success();
        }catch (Exception e){
            return CommonResult.error(e.toString());
        }
    }
}
