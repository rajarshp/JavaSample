/**
 * 
 */
package com.example.springboot.rest.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Joker
 *
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		List<String> errors = new ArrayList<String>();
		errors = Arrays.asList(exception.getMessage().split(","));

		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), errors, "");

		return Response.status(Status.BAD_REQUEST).entity(exceptionDetails).build();
	}

}
