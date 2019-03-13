/**
 * 
 */
package com.example.springboot.rest.service;

import com.example.springboot.rest.entity.Customer;

/**
 * @author Joker
 *
 */

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(long customerId, Customer customer);

	public Customer deleteCustomer(long customerId, Customer customer);

	public Customer findCustomer(long customerId);

}
