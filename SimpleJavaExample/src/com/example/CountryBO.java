package com.example;

public class CountryBO
{
 
	// Create country from given data, all data will be separated by comma(,)

	public Country createCountry(String data)
	{
		String[] input = data.split(",");

		if (input.length == 3)
		{
			Country ctr = new Country();

			ctr.setName(input[0]);
			ctr.setCountryCode(input[1]);
			ctr.setIsdCode(input[2]);
			return ctr;

		} else
		{
			System.out.println("Invalid data provided for create counter");
			return null;
		}

	}

	public void displayCountry(Country[] countryList, String countryName, int countryCount)
	{
		boolean flag = false;
		for (Country country : countryList)
		{
			if (country.getName().equals(countryName))
			{
				System.out.println(country);
				flag = true;
				break;
			} 
		}
		
		if(!flag)
		{
			System.out.println(countryName + " not present in the system");
		}
	}

	public void displayAllCountry(Country[] countryList, int countryCount)
	{
		for (Country country : countryList)
		{
			if(country != null)
			{
				System.out.println(country);
			}
		}
	}

	public void displayCountryAndCode(Country[] countryList, int countryCount)
	{
		for (Country country : countryList)
		{
			if(country != null)
			{
				System.out.println("country Name: " + country.getName() + " and Country code: " + country.getIsdCode());
			}
		}
	}

	public void updateIsdCodeOfSpecificCountry(Country[] countryList, String countryName, String isdCode,
			int countryCount)
	{
		for (Country country : countryList)
		{
			if (country != null && country.getName().equals(countryName))
			{
				country.setIsdCode(isdCode);
				System.out.println("Country details post update: "+country);
			}
		}
	}

}
