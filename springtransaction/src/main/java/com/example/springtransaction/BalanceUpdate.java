package com.example.springtransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class BalanceUpdate 
{
	private JdbcTemplate jdbcTemplate;
	private ArrayList<Balance> balanceList;
	
	public BalanceUpdate(JdbcTemplate jd)
	{
		this.jdbcTemplate = jd;
		this.balanceList = new ArrayList<Balance>();
	}
	
	public JdbcTemplate getJdbcTemplate() 
	{
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addList(ArrayList<Balance> bal)
	{
		balanceList = bal;
	}
	
	public void addBalance() throws Exception 
	{
		String sql = "insert into balance(acnum,balance) values(?,?)";
		
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					int k = 0;
					Balance bal = balanceList.get(i);
					
					ps.setInt(++k, bal.getAcnum());
					ps.setInt(++k, bal.getBalance());
					
				}
				
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return balanceList.size();
				}
			});
		
		

throw new Exception("Rollback");
	}
}
