package com.mycode.foodorderapp.controller;

import com.mycode.foodorderapp.model.FoodMenu;
import com.mycode.foodorderapp.service.MenuService;
import com.mycode.foodorderapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food/items")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/test/mysql")
    public ResponseEntity<Object> testMySql(){
        Object response = transactionService.testMethod();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test/mongodb")
    public ResponseEntity<Object> testMongoDb(){
        Object response = menuService.testMethod();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<FoodMenu>> getMenu(){
        List<FoodMenu> menuList = menuService.getMenu();

        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }

    @PostMapping("/db/test")
    public String dbTest(){
        return menuService.testDBPerformance();
    }
}

