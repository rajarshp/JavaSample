package com.example.springtransaction;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class DbUpdate 
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

	public void cretate()
	{
		try
		{
			for (int i = 1;i<6;i++)
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
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	public void executeDB() throws Exception
	{
		if(usrlist.size() >= 5)
			{
				CreateAccount newacc = new CreateAccount(jdbcTemplate);
				CreateUser newusr = new CreateUser(jdbcTemplate);
				BalanceUpdate newbal = new BalanceUpdate(jdbcTemplate);
				newacc.addList(acclist);
				newusr.addToList(usrlist);
				newbal.addList(ballist);
				
				newusr.execute(); // insert data to db
				newacc.addAccount(); // insert data to db
				newbal.addBalance(); // insert data to db
				
				//throw new Exception();
				
			}
		}
		
	
}
