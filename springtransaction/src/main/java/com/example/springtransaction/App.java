package com.example.springtransaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        
        ExecutorService ex = Executors.newFixedThreadPool(1);
        
        for (int i =0;i<1;i++)
        {
        	
        	ex.submit(db);
        }
        
        ex.shutdown();
        while(ex.isTerminated())
        {
        	System.out.println("All completed");
        }
        
//        try {
//        	db.cretate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        System.out.println("Done");
    }
}
