package com.example;

public class Country
{
	private String name;
	private String countryCode;
	private String isdCode;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getIsdCode()
	{
		return isdCode;
	}

	public void setIsdCode(String isdCode)
	{
		this.isdCode = isdCode;
	}

	@Override
	public String toString()
	{
		return "Country [name=" + name + ", countryCode=" + countryCode + ", isdCode=" + isdCode + "]";
	}
	
	
}
