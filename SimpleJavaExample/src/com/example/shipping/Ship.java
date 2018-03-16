package com.example.shipping;

public class Ship
{
	private int maximumWeight;
	private int currentShipWeight;
	private int refugee1;
	private int refugee2;
	private int refugee3;
	
	public int getMaximumWeight()
	{
		return maximumWeight;
	}
	public void setMaximumWeight(int maximumWeight)
	{
		this.maximumWeight = maximumWeight;
	}
	public int getCurrentShipWeight()
	{
		return currentShipWeight;
	}
	public void setCurrentShipWeight(int currentShipWeight)
	{
		this.currentShipWeight = currentShipWeight;
	}
	public int getRefugee1()
	{
		return refugee1;
	}
	public void setRefugee1(int refugee1)
	{
		this.refugee1 = refugee1;
	}
	public int getRefugee2()
	{
		return refugee2;
	}
	public void setRefugee2(int refugee2)
	{
		this.refugee2 = refugee2;
	}
	public int getRefugee3()
	{
		return refugee3;
	}
	public void setRefugee3(int refugee3)
	{
		this.refugee3 = refugee3;
	}
	
	public void numberOfRufugeeCanAccomodate()
	{
		int weight = this.maximumWeight - this.currentShipWeight;
		
		if( weight >= (this.refugee1 + this.refugee2 + this.refugee3))
		{
			System.out.println("can accomodate all 3 refugees");
		}
		else if( weight >= (this.refugee1 + this.refugee2) || weight >= (this.refugee1 + this.refugee3) || weight >= (this.refugee2 + this.refugee3 ))
		{
			System.out.println("can accomodate 2 refugees");
		}
		else if( weight >= this.refugee1 || weight >= this.refugee2 || weight >= this.refugee3)
		{
			System.out.println("can accomodate 1 refugee");
		}
		else
		{
			System.out.println("can not accomodate anyone");
		}
	}

}
