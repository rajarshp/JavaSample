package com.mycode.foodorderapp.controller;

import com.mycode.foodorderapp.model.Customer;
import com.mycode.foodorderapp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerContoller {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/new")
    public ResponseEntity<Customer> resgister(@RequestBody Customer customer){
        Customer resgisteredCustomer = customerService.registerCustomer(customer);

        return new ResponseEntity<>(resgisteredCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/email/check")
    public boolean checkEmail(@RequestParam String email){
        return customerService.checkAlreadyRegisteredEmail(email);
    }

    @PostMapping("/db/test")
    public String dbTest(){
        return customerService.testDBPerformance();
    }
}
