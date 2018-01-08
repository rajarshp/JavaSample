package com.example.springtransaction;

public class Transaction 
{
	private int fromac;
	private int toac;
	private int amount;
	
	public int getFromac() 
	{
		return fromac;
	}
	public void setFromac(int fromac) 
	{
		this.fromac = fromac;
	}
	public int getToac() 
	{
		return toac;
	}
	public void setToac(int toac) 
	{
		this.toac = toac;
	}
	public int getAmount() 
	{
		return amount;
	}
	public void setAmount(int amount) 
	{
		this.amount = amount;
	}
}
