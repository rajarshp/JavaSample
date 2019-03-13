/**
 * 
 */
package com.example.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.rest.entity.Customer;

/**
 * @author Joker
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByCustomerID(long cuatomerID);

	public Customer findByEmail(String email);

	public Customer findByPhone(String phone);

}
