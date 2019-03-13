/**
 * 
 */
package com.example.springboot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.rest.entity.Customer;
import com.example.springboot.rest.repository.CustomerRepository;

/**
 * @author Joker
 *
 */
@Service
public class CutomerServiceClass implements CustomerService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.springboot.rest.service.CustomerService#addCustomer(com.example.
	 * springboot.rest.entity.Customer)
	 */
	@Autowired
	private CustomerRepository customerRepositoty;

	@Override
	public Customer addCustomer(Customer customer) {

		return customerRepositoty.save(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.springboot.rest.service.CustomerService#updateCustomer(long,
	 * com.example.springboot.rest.entity.Customer)
	 */
	@Override
	public Customer updateCustomer(long customerId, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.springboot.rest.service.CustomerService#deleteCustomer(long,
	 * com.example.springboot.rest.entity.Customer)
	 */
	@Override
	public Customer deleteCustomer(long customerId, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.springboot.rest.service.CustomerService#findCustomer(long)
	 */
	@Override
	public Customer findCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
