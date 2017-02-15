package com.daniele.hibernate.service;

import java.util.List;

import com.daniele.hibernate.model.UserDetails;

public interface UserDetailsService {
	UserDetails getUserById(long id);
	UserDetails getUserFromSession(long id);
	UserDetails loadUserFromSession(long id);
	List<UserDetails> getAllUsers();
	int countUsers();
	void saveUserDetails(UserDetails userDetails);
}