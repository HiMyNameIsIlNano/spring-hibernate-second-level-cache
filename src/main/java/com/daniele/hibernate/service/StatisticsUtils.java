package com.daniele.hibernate.service;

import org.hibernate.stat.Statistics;

import com.daniele.hibernate.model.UserDetails;

public interface StatisticsUtils {
	void printData(UserDetails user, int count);
	void printStatitstics(int i);
	Statistics getStatitstics();
	boolean isStatitsticsEnabled();
}