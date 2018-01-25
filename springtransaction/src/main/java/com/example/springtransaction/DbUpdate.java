package com.example.springtransaction;

import java.util.ArrayList;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class DbUpdate  extends Thread
{
	private JdbcTemplate jdbcTemplate;
	private User usr;
	private Balance bal;
	private Account acc;
	
	private ArrayList<User> usrlist;
	private ArrayList<Balance> ballist;
	private ArrayList<Account> acclist;
	private CreateUser newusr;
	
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
	
	public void run()
	{
		create();
	}

	//@Transactional(rollbackFor={Exception.class})
	public void create()
	{
		try
		{
			StopWatch sw = new StopWatch();
			int counter = 1000;
			sw.start();
			for (int i = 1;i<=100000;i++)
			{
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
				
				if(usrlist.size() >= 1000)
				{
					executeDB(counter);
					sw.suspend();
					usrlist.clear();
					ballist.clear();
					acclist.clear();
					System.out.println(counter + " updated: "+sw);
					counter +=1000;
					
					sw.resume();
				}
			}
			sw.stop();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	public void executeDB(int count) throws Exception
	{
		
				CreateAccount newacc = new CreateAccount(jdbcTemplate);
				CreateUser newusr = new CreateUser(jdbcTemplate);
				//BalanceUpdate newbal = new BalanceUpdate(jdbcTemplate);
				newacc.addList(acclist);
				newusr.addToList(usrlist);
				//newbal.addList(ballist);
				
				newusr.execute(); // insert data to db
				newacc.addAccount(); // insert data to db
				//newbal.addBalance(); // insert data to db
				
				newacc.getAccList().clear();
				newusr.getUserList().clear();
				//newbal.getBalanceList().clear();
				if(count == 5000)
				{
					Thread.sleep(1000);
					throw new Exception("Rollback");
				}
				count += 1000;
				//throw new Exception();
				
			
		}
		
	
}
