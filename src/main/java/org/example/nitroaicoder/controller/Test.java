package org.example.nitroaicoder.controller;

import org.example.nitroaicoder.common.BaseResponse;
import org.example.nitroaicoder.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/hello")
    public BaseResponse<String> helloCheck(){
        return ResultUtils.success("hello world");
    }
}
