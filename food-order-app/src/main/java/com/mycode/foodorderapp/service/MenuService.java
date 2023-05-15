package com.mycode.foodorderapp.service;

import com.mycode.foodorderapp.model.Customer;
import com.mycode.foodorderapp.model.FoodMenu;
import com.mycode.foodorderapp.repository.MenuRepository;
import com.mycode.foodorderapp.util.SampleUserDataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private SampleUserDataGenerator sampleUserDataGenerator;

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

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public String testDBPerformance() {
        List<FoodMenu> customerList = sampleUserDataGenerator.generateSampleMenuData();
        int numThreads = 4; // Set the number of threads
        int batchSize = 1000; // Set the batch size

        List<List<FoodMenu>> partitions = new ArrayList<>();
        for (int i = 0; i < customerList.size(); i += batchSize) {
            int end = Math.min(i + batchSize, customerList.size());
            partitions.add(customerList.subList(i, end));
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = new ArrayList<>();

        for (List<FoodMenu> partition : partitions) {
            futures.add(executor.submit(() -> {
                try {
                    insertBatch(partition);
                } catch (Exception e) {
                    log.error("Failed to insert partition: {}", partition, e);
                    throw e;
                }
            }));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                log.error("Partition insertion failed", e);
            }
        }

        executor.shutdown();


        return "Success";
    }

  @Transactional
    public void insertBatch(List<FoodMenu> batch) {
        menuRepository.saveAll(batch);
        log.info(batch.size() + " records persisted in the db");
    }

    @Recover
    public void handleInsertBatchException(Exception e, List<Customer> batch) {
        log.error("Insertion failed for batch: {}", batch, e);
        // Handle the exception here, for example, you can retry with a smaller batch size
    }
}
