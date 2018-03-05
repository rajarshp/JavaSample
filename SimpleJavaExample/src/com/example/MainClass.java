package com.example;

public class MainClass
{
 
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		CountryBO cbo = new CountryBO();
		Country[] countryList =new Country[5];

		countryList[0] = cbo.createCountry("India,91,91"); // data should be in this format(countryname,countrycode,isdcode)
		countryList[1] = cbo.createCountry("USA,1,1");
		countryList[2] = cbo.createCountry("UK,44,44");

		cbo.displayAllCountry(countryList, countryList.length);
		cbo.displayCountry(countryList, "UK", countryList.length);
		cbo.displayCountryAndCode(countryList, countryList.length);
		cbo.updateIsdCodeOfSpecificCountry(countryList, "USA", "01", countryList.length);

	}

}
