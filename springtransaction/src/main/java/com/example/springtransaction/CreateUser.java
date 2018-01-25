package com.example.springtransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateUser 
{
	private JdbcTemplate jdbcTemplate;
	private ArrayList<User> userList;

	public JdbcTemplate getJdbcTemplate() 
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public CreateUser(JdbcTemplate jdbcTemplate) 
	{
		this.userList = new ArrayList<User>();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addToList(ArrayList<User> usr)
	{
		userList = usr;
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}

	public boolean execute()
	{
		String sql = "insert into userdetails(id,firstname,lastname) values(?,?,?)";
		
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int i) throws SQLException  {
					// TODO Auto-generated method stub
					int k = 0;
					User usr = userList.get(i);
					
					ps.setInt(++k, usr.getId());
					ps.setString(++k, usr.getFirstName());
					ps.setString(++k, usr.getLastName());
					
					
				}
				
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return userList.size();
				}
			});
		
		
		
		return true;
	}
}
