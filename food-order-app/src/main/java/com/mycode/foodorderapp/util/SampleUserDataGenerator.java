package com.mycode.foodorderapp.util;

import com.mycode.foodorderapp.model.Customer;
import com.mycode.foodorderapp.model.FoodMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class SampleUserDataGenerator {



    public List<Customer> generateSampleUserData() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Split the user generation task into multiple smaller tasks
        List<Callable<List<Customer>>> tasks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            tasks.add(() -> {
                List<Customer> userList = new ArrayList<>();
                for (int j = 0; j < 1000; j++) {
                    Customer customer = new Customer();
                    customer.setFirstName("FirstName " + finalI + "-" + j);
                    customer.setLastName("LastName " + finalI + "-" + j);
                    customer.setEmail("user" + finalI + "-" + j + "@example.com");
                    customer.setPassword("test");
                    userList.add(customer);
                }
                return userList;
            });
        }

        // Execute the tasks in parallel using multi-threading
        List<Future<List<Customer>>> futures;
        try {
            futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            // Handle the exception
            log.error("Failed to generate sample user data: " + e.getMessage());
            return null;
        }

        // Collect the results from the threads
        List<Customer> customerList = new ArrayList<>();
        for (Future<List<Customer>> future : futures) {
            try {
                customerList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                // Handle the exception
                log.error("Failed to generate sample user data: " + e.getMessage());
            }
        }

        // Shutdown the executor service
        executorService.shutdown();
        log.info("Customer Size "+customerList.size());
        return customerList;
    }

    public List<FoodMenu> generateSampleMenuData() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        // Split the user generation task into multiple smaller tasks
        List<Callable<List<FoodMenu>>> tasks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            tasks.add(() -> {
                List<FoodMenu> foodMenuList = new ArrayList<>();
                for (int j = 0; j < 1000; j++) {
                    FoodMenu foodMenu = new FoodMenu();
                    foodMenu.setName("Menu " + finalI + "-" + j);
                    foodMenu.setDescription("Menu " + finalI + "-" + j);
                    foodMenu.setPrice(j);
                    foodMenuList.add(foodMenu);
                }
                return foodMenuList;
            });
        }

        // Execute the tasks in parallel using multi-threading
        List<Future<List<FoodMenu>>> futures;
        try {
            futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            // Handle the exception
            log.error("Failed to generate sample user data: " + e.getMessage());
            return null;
        }

        // Collect the results from the threads
        List<FoodMenu> foodMenuList = new ArrayList<>();
        for (Future<List<FoodMenu>> future : futures) {
            try {
                foodMenuList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                // Handle the exception
                log.error("Failed to generate sample user data: " + e.getMessage());
            }
        }

        // Shutdown the executor service
        executorService.shutdown();
log.info("Menu Size "+foodMenuList.size());
        return foodMenuList;
    }

}
