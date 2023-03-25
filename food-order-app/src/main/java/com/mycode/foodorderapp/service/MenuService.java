package com.mycode.foodorderapp.service;

import com.mycode.foodorderapp.model.FoodMenu;
import com.mycode.foodorderapp.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Object testMethod(){
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setName("Test");
        foodMenu.setDescription("test");
        foodMenu.setPrice(100);

        String id = menuRepository.save(foodMenu).getId();
        log.info("Items created " + id);
        return menuRepository.findById(id);
    }

    public List<FoodMenu> getMenu() {
        return menuRepository.findAll();
    }
}
