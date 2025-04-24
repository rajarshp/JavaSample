package com.mycode.foodorderapp.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Testsomething {
    @Autowired
    private Dosomething dosomethingImpl;

    public String process() {

        return dosomethingImpl.displaySomething();
    }
}
