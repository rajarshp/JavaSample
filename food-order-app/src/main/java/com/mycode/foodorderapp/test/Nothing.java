package com.mycode.foodorderapp.test;

import org.springframework.stereotype.Component;

@Component
public class Nothing implements Dosomething{
    @Override
    public String displaySomething() {
        return "This is Nothing";
    }
}
