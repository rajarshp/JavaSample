/**
 * 
 */
package com.example.springboot.rest.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

/**
 * @author Rajarshi Pain
 *
 */
@Entity
@Table(name = "customer")
@XmlRootElement
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerid")
	long customerID;

	@NotNull
	@Length(min = 1, max = 100, message = "Firstname Should not be more than 100 characters")
	@Column(name = "firstname")
	private String firstName;

	@NotNull
	@Length(min = 1, max = 100, message = "Lastname Should not be more than 100 characters")
	@Column(name = "lastname")
	private String lastName;

	@NotNull
	@Length(min = 1, max = 300, message = "Enter a valid email id")
	@Column(name = "email")
	private String email;

	@NotNull
	@Length(min = 1, max = 10, message = "Enter a valid phone number")
	@Column(name = "phone")
	private String phone;

	@NotNull
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@NotNull(message = "Enter address")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressid")
	private AddressDetails address;

	/**
	 * @return the customerID
	 */
	public long getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public AddressDetails getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressDetails address) {
		this.address = address;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [customerID=");
		builder.append(customerID);
		builder.append(", \nfirstName=");
		builder.append(firstName);
		builder.append(", \nlastName=");
		builder.append(lastName);
		builder.append(", \nemail=");
		builder.append(email);
		builder.append(", \nphone=");
		builder.append(phone);
		builder.append(", \ndateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", \naddress=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
