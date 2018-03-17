package com.example.shipping;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
				
		int maxWeight= scanner.nextInt();
		int currentWeight= scanner.nextInt();
		int refugee1= scanner.nextInt();
		int refugee2= scanner.nextInt();
		int refugee3= scanner.nextInt();
		
		Main main = new Main();
		main.numberOfRufugeeCanAccomodate(maxWeight, currentWeight, refugee1, refugee2, refugee3);

	}
	
	public void numberOfRufugeeCanAccomodate(int maxWeight,int currentWeight,int refugee1,int refugee2, int refugee3)
	{
		int weight = maxWeight - currentWeight;
		
		if( weight >= (refugee1 + refugee2 + refugee3))
		{
			System.out.println("3");
		}
		else if( weight >= (refugee1 + refugee2) || weight >= (refugee1 + refugee3) || weight >= (refugee2 + refugee3 ))
		{
			System.out.println("2");
		}
		else if( weight >= refugee1 || weight >= refugee2 || weight >= refugee3)
		{
			System.out.println("1");
		}
		else
		{
			System.out.println("0");
		}
	}


}
