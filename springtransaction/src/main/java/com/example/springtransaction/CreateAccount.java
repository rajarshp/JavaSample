package com.example.springtransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateAccount 
{
	private JdbcTemplate jdbcTemplate;
	private ArrayList<Account> accList;
	
	public CreateAccount(JdbcTemplate jd)
	{
		this.jdbcTemplate = jd;
		this.accList = new ArrayList<Account>();
	}
	
	public CreateAccount()
	{
		this.accList = new ArrayList<Account>();
	}
	
	public JdbcTemplate getJdbcTemplate() 
	{
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addList(ArrayList<Account> acc)
	{
		accList = acc;
	}
	
	public ArrayList<Account> getAccList() {
		return accList;
	}

	public void addAccount(int count) throws Exception 
	{
		String sql = "insert into account(acnumber,userid) values(?,?)";
		
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					int k = 0;
					Account acc = accList.get(i);
					
					ps.setInt(++k, acc.getAcnumber());
					ps.setInt(++k, acc.getUserid());
					
				}
				
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return accList.size();
				}
			});
			System.out.println("New accounts acreated "+count);
//			if(count ==5000)
//			{
//				throw new Exception();
//			}
			//throw new Exception();
		//return true;
	}
}
