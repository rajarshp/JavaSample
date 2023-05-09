package com.mycode.foodorderapp.repository;

import com.mycode.foodorderapp.model.FoodMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MenuRepository extends MongoRepository<FoodMenu,String> {
}
