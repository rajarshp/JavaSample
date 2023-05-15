package com.mycode.foodorderapp.service;

import com.mycode.foodorderapp.model.Customer;
import com.mycode.foodorderapp.repository.CustomerRepository;
import com.mycode.foodorderapp.util.SampleUserDataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private SampleUserDataGenerator sampleUserDataGenerator;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private final StopWatch stopWatch = new StopWatch();

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
    public void insertBatch(List<Customer> lists) {
        stopWatch.start();
       // TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        try {
            // customerRepository.saveAll(lists);
            int batchSize = 1000;
            List<List<Customer>> batches = ListUtils.partition(lists, batchSize);
            ExecutorService executor = Executors.newFixedThreadPool(10);
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            for (List<Customer> batch : batches) {
                CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                        transactionTemplate.execute(status -> {
                            try {
                                insertPartition(batch);
                                return null;
                            } catch (Exception e) {
                                log.error("Failed to insert partition: {}", batch, e);
                                throw new RuntimeException(e);
                            }
                        });
                    } catch (Exception e) {
                        log.error("Failed to execute transaction for partition: {}", batch, e);
                        throw new RuntimeException(e);
                    }
                    return null;
                }, executor);
                futures.add(future);
            }
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            executor.shutdown();

            stopWatch.stop();
            log.info("Data Saved in " + stopWatch.toString());
            stopWatch.reset();
        }
        catch (Exception e) {
//            if (transactionStatus.isRollbackOnly()) {
//                log.error("Transaction has been rolled back");
//            } else {
//                log.error("An error occurred during data insertion", e);
//            }
            throw e;
        }
    }

    @Recover
    public void handleInsertBatchException(Exception e, List<Customer> batch) {
        log.error("Insertion failed for batch: {}", batch, e);
        // Handle the exception here, for example, you can retry with a smaller batch size
    }

   @Transactional//(propagation = Propagation.REQUIRES_NEW)
    private void insertPartition(List<Customer> partition) {
        customerRepository.saveAll(partition);
        log.info("Data Saved: " + partition.size());
    }
}
