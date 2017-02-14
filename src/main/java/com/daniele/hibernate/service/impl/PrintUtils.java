package com.daniele.hibernate.service.impl;

import com.daniele.hibernate.model.UserDetails;

public interface PrintUtils {
	void printData(UserDetails user, int count);
	void printStats(int i);
}