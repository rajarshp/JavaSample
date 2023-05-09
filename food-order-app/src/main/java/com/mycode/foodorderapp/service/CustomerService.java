package com.mycode.foodorderapp.service;

import com.mycode.foodorderapp.model.Customer;
import com.mycode.foodorderapp.repository.CustomerRepository;
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
public class CustomerService {
    @Autowired
    private SampleUserDataGenerator sampleUserDataGenerator;

    @Autowired
    private CustomerRepository customerRepository;
    public Customer registerCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        newCustomer.setPassword("");
        log.info("new customer created "+newCustomer);
        return newCustomer;
    }

    public boolean checkAlreadyRegisteredEmail(String email) {
        int count = customerRepository.countByEmail(email);
        log.info("Already registered email? count ="+count);
        return count >1;
    }

    public String testDBPerformance() {
        List<Customer> customerList = sampleUserDataGenerator.generateSampleUserData();
        insertBatch(customerList);

        return "Success";
    }

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public void insertBatch(List<Customer> batch) {
        int numThreads = 4; // Set the number of threads
        int batchSize = 1000; // Set the batch size

        List<List<Customer>> partitions = new ArrayList<>();
        for (int i = 0; i < batch.size(); i += batchSize) {
            int end = Math.min(i + batchSize, batch.size());
            partitions.add(batch.subList(i, end));
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = new ArrayList<>();

        for (List<Customer> partition : partitions) {
            futures.add(executor.submit(() -> {
                try {
                    customerRepository.saveAllAndFlush(partition);
                    log.info("Customer Saved "+ partition.size());
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
    }

    @Recover
    public void handleInsertBatchException(Exception e, List<Customer> batch) {
        log.error("Insertion failed for batch: {}", batch, e);
        // Handle the exception here, for example, you can retry with a smaller batch size
    }
}
