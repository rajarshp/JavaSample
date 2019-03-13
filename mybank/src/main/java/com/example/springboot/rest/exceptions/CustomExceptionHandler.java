/**
 * 
 */
package com.example.springboot.rest.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Joker
 *
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	public Response toResponse(Throwable tx) {

		if (tx instanceof NotFoundException) {

			List<String> errors = new ArrayList<String>();
			errors.add(tx.getMessage());
			ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), errors, "");

			return Response.status(Status.BAD_REQUEST).entity(exceptionDetails).build();
		} else {
			List<String> errors = new ArrayList<String>();
			errors.add(tx.getMessage());
			ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), errors, "");

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exceptionDetails).build();
		}
	}
}
