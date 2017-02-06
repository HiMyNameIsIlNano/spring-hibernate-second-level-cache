package com.daniele.hibernate.service.impl;

import com.daniele.hibernate.model.UserDetails;

public interface PrintUtils {
	void printStats();
	void printData(UserDetails user, int count);
}