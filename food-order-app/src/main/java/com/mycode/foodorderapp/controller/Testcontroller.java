package com.mycode.foodorderapp.controller;

import com.mycode.foodorderapp.test.Testsomething;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontroller {

    @Autowired
    private Testsomething testsomething;

    @GetMapping("/test/something")
    public String testSomething(){
        return testsomething.process();
    }
}
