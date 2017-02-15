package com.daniele.hibernate.main;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daniele.hibernate.model.Address;
import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.impl.StatisticsUtilsImpl;
import com.daniele.hibernate.service.impl.UserDetailsServiceImpl;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDetailsServiceImpl userDetailsServiceImpl = (UserDetailsServiceImpl) context.getBean("userDetailsServiceImpl");
		StatisticsUtilsImpl statisticsUtilsImpl = (StatisticsUtilsImpl) context.getBean("statisticsUtilsImpl");
		
		if (statisticsUtilsImpl != null) { 
			System.out.println("statisticsUtilsImpl is not null"); 
		} else {
			System.out.println("statisticsUtilsImpl is null");
		}
		
		for (int i = 1; i <= 5; i++) {
			Address address = new Address();
			address.setCity("City" + i);
			address.setStreet("Dummy Street N." + i);
			address.setZipcode("9009" + i);
			
			
			UserDetails user = new UserDetails();
			user.setName("User" + i);
			user.setDescription("Description field " + i);
			user.setAddress(address);
			user.setJoinDate(new Date());
			
			address.setUserDetails(user);
			
			userDetailsServiceImpl.saveUserDetails(user);
			System.out.println("Saving user" + i);
		}
				
		UserDetails user = userDetailsServiceImpl.getUserById(1);
		System.out.println(user.toString());
		
		((ClassPathXmlApplicationContext) context).close();
	}
}