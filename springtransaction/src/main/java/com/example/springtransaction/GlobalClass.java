package com.example.springtransaction;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class GlobalClass 
{
	private  static JdbcTemplate jdbcTemplate;
	private  static DataSource dataSource;

	
	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public static void setJdbcTemplate(JdbcTemplate jd) 
	{
		jdbcTemplate = jd;
	}
	public static  DataSource getDataSource() 
	{
		return dataSource;
	}
	public static void setDataSource(DataSource datasrc) 
	{
		dataSource = datasrc;
		
	}
	
	
	public static void load()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
		GlobalClass gs = (GlobalClass) ctx.getBean("startit");
	
	}
}
