package com.daniele.hibernate.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.hibernate.model.UserDetails;

@Service
public class PrintUtilsImpl implements PrintUtils {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void printStats(int i) {
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("******** Execution: " + i + " ********");
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
	}

	@Override
	public void printData(UserDetails user, int count) {
		System.out.println(count + ":: Name= " + user.getName() + ", Zipcode= " + user.getAddress().getZipcode());
		printStats(count);
	}
}
