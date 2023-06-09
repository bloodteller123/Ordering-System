package com.zihanc.takeout.controller;

import com.zihanc.takeout.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@Slf4j
public class FileController {
    @PostMapping("/")
    public CommonResult upload(){
        return CommonResult.success();
    }
}
