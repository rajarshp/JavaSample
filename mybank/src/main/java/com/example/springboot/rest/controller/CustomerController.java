/**
 * 
 */
package com.example.springboot.rest.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.springboot.rest.entity.Customer;
import com.example.springboot.rest.service.CutomerServiceClass;

/**
 * @author Joker
 *
 */

@Controller
@Path("/customers")
public class CustomerController {

	@Autowired
	private CutomerServiceClass customerService;

	@GET
	@Path("/welcome")
	public String welcome() {
		System.out.println("WELCOME");

		return "welcome";
	}

	@POST
	@Path("/add")
	@Consumes("application/json,application/xml")
	@Produces("application/json,application/xml")
	public ResponseEntity<Customer> add(@Valid Customer customer) {

		Customer result = customerService.addCustomer(customer);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
