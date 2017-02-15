package com.daniele.hibernate.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.StatisticsUtils;

@Service
public class StatisticsUtilsImpl implements StatisticsUtils {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Statistics getStatitstics() {
		return sessionFactory.getStatistics();
	}
	
	@Override
	public boolean isStatitsticsEnabled() {
		return sessionFactory.getStatistics().isStatisticsEnabled();
	}
	
	@Override
	public void printStatitstics(int i) {
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Execution: " + i);
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
	}

	@Override
	public void printData(UserDetails user, int count) {
		System.out.println(count + ":: Name= " + user.getName() + ", Zipcode= " + user.getAddress().getZipcode());
		printStatitstics(count);
	}
}
