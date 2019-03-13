/**
 * 
 */
package com.example.springboot.rest.exceptions;

import java.util.Date;
import java.util.List;

/**
 * @author Joker
 *
 */
public class ExceptionDetails {

	private Date timestamp;
	private List<String> messages;
	private String details;

	/**
	 * @param timestamp
	 * @param messages
	 * @param details
	 */
	public ExceptionDetails(Date timestamp, List<String> messages, String details) {
		super();
		this.timestamp = timestamp;
		this.messages = messages;
		this.details = details;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExceptionDetails [timestamp=");
		builder.append(timestamp);
		builder.append(", \nmessages=");
		builder.append(messages);
		builder.append(", \ndetails=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}

}
