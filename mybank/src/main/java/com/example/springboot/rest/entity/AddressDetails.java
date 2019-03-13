/**
 * 
 */
package com.example.springboot.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

/**
 * @author Joker
 *
 */

@Entity
@Table(name = "address")
@XmlRootElement
public class AddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addressid")
	long addressID;

	@NotNull
	@Length(min = 1, max = 100, message = "house no Should not be more than 100 characters")
	@Column(name = "houseno", length = 100)
	String houseNO;

	@NotNull
	@Length(min = 1, max = 100, message = "Street Should not be more than 100 characters")
	@Column(name = "street", length = 100)
	String street;

	@NotNull
	@Length(min = 1, max = 100, message = "City Should not be more than 100 characters")
	@Column(name = "city", length = 100)
	String city;

	@NotNull
	@Length(min = 1, max = 100, message = "State Should not be more than 100 characters")
	@Column(name = "state", length = 100)
	String state;

	@NotNull
	@Length(min = 1, max = 100, message = "Country Should not be more than 100 characters")
	@Column(name = "country", length = 100)
	String country;

	@NotNull
	@Length(min = 1, max = 6, message = "Enter a valid zipcode")
	@Column(name = "zipcode", length = 6)
	String zipcode;

	/**
	 * @return the houseNO
	 */
	public String getHouseNO() {
		return houseNO;
	}

	/**
	 * @param houseNO the houseNO to set
	 */
	public void setHouseNO(String houseNO) {
		this.houseNO = houseNO;
	}

	/**
	 * @return the road
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param road the road to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [houseNO=");
		builder.append(houseNO);
		builder.append(", \nstreet=");
		builder.append(street);
		builder.append(", \ncity=");
		builder.append(city);
		builder.append(", \nstate=");
		builder.append(state);
		builder.append(", \ncountry=");
		builder.append(country);
		builder.append(", \nzipcode=");
		builder.append(zipcode);
		builder.append("]");
		return builder.toString();
	}

}
