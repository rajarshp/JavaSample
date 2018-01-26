package com.example.springtransaction;

import java.util.ArrayList;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class DbUpdate  //extends Thread
{
	private JdbcTemplate jdbcTemplate;
	private User usr;
	private Balance bal;
	private Account acc;
	private int count;
	private ArrayList<User> usrlist;
	private ArrayList<Balance> ballist;
	private ArrayList<Account> acclist;
	private CreateUser newusr;
	private CreateAccount newacc;
	
	public DbUpdate() 
	{

		this.usrlist = new ArrayList<User>();
		this.ballist = new ArrayList<Balance>();
		this.acclist = new ArrayList<Account>();
	}
	
	public DbUpdate(JdbcTemplate jd) 
	{
		this.jdbcTemplate = jd;
		this.usrlist = new ArrayList<User>();
		this.ballist = new ArrayList<Balance>();
		this.acclist = new ArrayList<Account>();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	public void run()
//	{
//		create();
//	}

	
	public CreateUser getNewusr() {
		return newusr;
	}

	public void setNewusr(CreateUser newusr) {
		this.newusr = newusr;
	}

	public CreateAccount getNewacc() {
		return newacc;
	}

	public void setNewacc(CreateAccount newacc) {
		this.newacc = newacc;
	}

	public void create(int i)
	{
		try
		{
			StopWatch sw = new StopWatch();
			int counter = 1000;
			sw.start();
//			for (int i = 1;i<=100000;i++)
//			{
				usr = new User();
				bal = new Balance();
				acc = new Account();
				
				usr.setId(i);
				usr.setFirstName("first"+i);
				usr.setLastName("last"+i);
				
				acc.setAcnumber(1000+i);
				acc.setUserid(i);
				
				bal.setAcnum(1000+i);
				bal.setBalance(5000+i);
				
				usrlist.add(usr);
				ballist.add(bal);
				acclist.add(acc);
				
//				if(usrlist.size() >= 1000)
//				{
//					executeDB(counter);
//					sw.suspend();
//					usrlist.clear();
//					ballist.clear();
//					acclist.clear();
//					System.out.println(counter + " updated: "+sw);
//					counter +=1000;
//					
//					sw.resume();
//				}
//			}
			sw.stop();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
	public void executeDB() throws Exception
	{
		//System.out.println("Size: "+usrlist.size());
//				CreateAccount newacc = new CreateAccount(jdbcTemplate);
//				CreateUser newusr = new CreateUser(jdbcTemplate);
				//BalanceUpdate newbal = new BalanceUpdate(jdbcTemplate);
				count += 1000;
				getNewacc().addList(acclist);
				getNewusr().addToList(usrlist);
				//newbal.addList(ballist);
				
				getNewusr().execute(); // insert data to db
				//System.out.println("New users created " + count);
				//Thread.sleep(10000000);
				//getNewacc().addAccount(count); // insert data to db
				//newbal.addBalance(); // insert data to db
				//System.out.println("New accounts created " + count);
				getNewacc().getAccList().clear();
				getNewusr().getUserList().clear();
				//newbal.getBalanceList().clear();
//				if(count == 5000)
//				{
//					Thread.sleep(1000);
//					throw new Exception("Rollback");
//				}
				
				//Thread.sleep(10000);
//				if(count == 5000)
//				{
//					throw new Exception();
//				}
				Thread.sleep(10000);
				throw new Exception("Rollback");
			
		}
		
	
	
	
}
