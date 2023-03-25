package com.mycode.foodorderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("food-items")
@Data
public class FoodMenu {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
}
