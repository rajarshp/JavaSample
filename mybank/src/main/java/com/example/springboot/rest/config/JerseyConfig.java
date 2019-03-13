/**
 * 
 */
package com.example.springboot.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.springboot.rest.controller.CustomerController;
import com.example.springboot.rest.exceptions.ConstraintViolationExceptionMapper;
import com.example.springboot.rest.exceptions.CustomExceptionHandler;

/**
 * @author Joker
 *
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

//		packages("com.example.springboot.rest");

		register(CustomerController.class);
		register(CustomExceptionHandler.class);
		register(ConstraintViolationExceptionMapper.class);
	}

}
