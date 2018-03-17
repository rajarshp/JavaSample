package com.example.redcross;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		
		
		int xyzIndex=0;
		int pqrIndex=0;
		
		int xyzCount=0;
		int pqrCount=0;
		
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		String line = scanner.next();
		
		while(xyzIndex != -1 && pqrIndex != -1)
		{
			String xyz = "xyz";
			String pqr = "pqr";
			
			xyzIndex = line.indexOf(xyz,xyzIndex);

		    if(xyzIndex != -1)
		    {
		        xyzCount ++;
		        xyzIndex += xyz.length();
		    }
		    
		    pqrIndex = line.indexOf(pqr,pqrIndex);

		    if(pqrIndex != -1)
		    {
		        pqrCount ++;
		        pqrIndex += pqr.length();
		    }
		}
		
		System.out.println(xyzCount +" " + pqrCount);

	}

}
