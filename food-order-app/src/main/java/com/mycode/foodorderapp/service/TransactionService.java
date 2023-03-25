package com.mycode.foodorderapp.service;

import com.mycode.foodorderapp.model.Transaction;
import com.mycode.foodorderapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Object testMethod(){
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(100);
        transaction.setTransactionStatus("success");
        Long id = transactionRepository.save(transaction).getTransactionId();
        return transactionRepository.findById(id);
    }
}
