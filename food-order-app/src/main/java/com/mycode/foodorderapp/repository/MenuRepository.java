package com.mycode.foodorderapp.repository;

import com.mycode.foodorderapp.model.FoodMenu;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MenuRepository extends MongoRepository<FoodMenu,String> {
}
