package com.mycode.foodorderapp.repository;

import com.mycode.foodorderapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    int countByEmail(String email);
}
