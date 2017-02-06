package com.daniele.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.hibernate.dao.UserDetailsDao;
import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.UserDetailsService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Override
	public void insertDummyData(int nRecords) { 
		userDetailsDao.insertDummyData(nRecords);
	}
	
	@Override
	public UserDetails getUserById(long id) {
		return userDetailsDao.getUserById(id);
	}
	
	@Override
	public UserDetails getUserFromSession(long id) {
		return userDetailsDao.getUserFromSession(id);
	}

	@Override
	public UserDetails loadUserFromSession(long id) {
		return userDetailsDao.loadUserFromSession(id);
	}
	
	@Override
	public List<UserDetails> getAllUsers() {
		return userDetailsDao.getAllUsers();
	}
}