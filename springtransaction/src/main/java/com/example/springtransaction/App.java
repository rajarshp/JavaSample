package com.example.springtransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
        //GlobalClass.load();
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        
        DbUpdate db = ctx.getBean("dbupdate",DbUpdate.class);
        
        //DbUpdate db = new DbUpdate(GlobalClass.getJdbcTemplate());
        db.cretate();
        try {
			db.executeDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Done");
    }
}
