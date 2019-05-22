package com.xa.demo.controller;

import com.xa.demo.service.AsynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsynController {

    @Autowired
    AsynService asynService;

    @GetMapping("/hello")
    public String hello(){

        //会sleep三秒钟
        asynService.hello();
        return "success";
    }


}
