package com.daniele.hibernate.dao;

import java.util.List;

import com.daniele.hibernate.model.UserDetails;

public interface UserDetailsDao {
	void insertDummyData(int nRecords);
	UserDetails getUserById(long id);
	UserDetails getUserFromSession(long id);
	UserDetails loadUserFromSession(long id);
	List<UserDetails> getAllUsers();
	int countUsers();
}