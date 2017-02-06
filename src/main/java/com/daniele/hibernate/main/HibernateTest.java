package com.daniele.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.impl.PrintUtilsImpl;
import com.daniele.hibernate.service.impl.UserDetailsServiceImpl;

public class HibernateTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDetailsServiceImpl userDetailsServiceImpl = (UserDetailsServiceImpl) context.getBean("userDetailsServiceImpl");
		PrintUtilsImpl printUtilsImpl = (PrintUtilsImpl) context.getBean("printUtilsImpl");
		
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
		
		System.out.println(userDetailsServiceImpl.getUserFromSession(1).toString());
		System.out.println(userDetailsServiceImpl.getUserById(1).toString());
		
		printUtilsImpl.printStats();
		
		UserDetails user = null;
		user = userDetailsServiceImpl.loadUserFromSession(1L);
		/*printUtilsImpl.printData(user, 1);
		
		user = userDetailsServiceImpl.loadUserFromSession(2L);
		printUtilsImpl.printData(user, 2);
		
		// clear first level cache, so that second level cache is used
		// session.evict(user);
		user = userDetailsServiceImpl.loadUserFromSession(1L);
		printUtilsImpl.printData(user, 3);
		
		user = userDetailsServiceImpl.loadUserFromSession(3L);
		printUtilsImpl.printData(user, 4);
		
		// user = (UserDetails) otherSession.load(UserDetails.class, 1L);
		printUtilsImpl.printData(user, 5);*/
		
		((ClassPathXmlApplicationContext) context).close();
	}
}