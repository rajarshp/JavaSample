package com.example.shipping;

import java.util.Scanner;

public class SolutionForCMD
{
	

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter maximm weight:");
		int maxWeight= scanner.nextInt();
		
		System.out.println("Enter current weight:");
		int currentWeight= scanner.nextInt();
		
		System.out.println("Enter weight of refugee1:");
		int refugee1= scanner.nextInt();
		
		System.out.println("Enter weight of refugee2:");
		int refugee2= scanner.nextInt();
		
		System.out.println("Enter weight of refugee3:");
		int refugee3= scanner.nextInt();
		
		Ship ship = new Ship();
		ship.setMaximumWeight(maxWeight);
		ship.setCurrentShipWeight(currentWeight);
		ship.setRefugee1(refugee1);
		ship.setRefugee2(refugee2);
		ship.setRefugee3(refugee3);
		
		ship.numberOfRufugeeCanAccomodate();
		

	}
	
	

}
