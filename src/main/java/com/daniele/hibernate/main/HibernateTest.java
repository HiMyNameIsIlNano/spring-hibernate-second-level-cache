package com.daniele.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daniele.hibernate.service.impl.StatisticsUtilsImpl;
import com.daniele.hibernate.service.impl.UserDetailsServiceImpl;

public class HibernateTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDetailsServiceImpl userDetailsServiceImpl = (UserDetailsServiceImpl) context.getBean("userDetailsServiceImpl");
		StatisticsUtilsImpl printUtilsImpl = (StatisticsUtilsImpl) context.getBean("printUtilsImpl");
		
		if (userDetailsServiceImpl != null) { 
			userDetailsServiceImpl.insertDummyData(5); 
		} else {
			System.out.println("userDetailsServiceImpl is null"); 
		}
		
		if (printUtilsImpl != null) { 
			System.out.println("userDetailsServiceImpl is not null"); 
		} else {
			System.out.println("userDetailsServiceImpl is null");
		}
				
		((ClassPathXmlApplicationContext) context).close();
	}
}