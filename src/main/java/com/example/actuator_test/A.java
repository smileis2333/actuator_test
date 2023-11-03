package com.example.actuator_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A {
    @Autowired
    private BuildProperties buildProperties;
    @GetMapping("test")
    public String aa() {
        return "11";
    }
}
