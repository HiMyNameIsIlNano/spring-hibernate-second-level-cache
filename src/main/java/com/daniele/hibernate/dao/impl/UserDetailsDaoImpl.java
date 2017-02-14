package com.daniele.hibernate.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.hibernate.dao.UserDetailsDao;
import com.daniele.hibernate.model.Address;
import com.daniele.hibernate.model.UserDetails;

@Repository
@Transactional
public class UserDetailsDaoImpl implements UserDetailsDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
    
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public void insertDummyData(int nRecords) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 1; i <= nRecords; i++) {
			UserDetails user = new UserDetails();
			user.setName("User" + i);
			user.setDescription("Description field " + i);
			
			Address address = new Address();
			address.setCity("City" + i);
			address.setStreet("Dummy Street N." + i);
			address.setZipcode("9009" + i);
			address.setUserDetails(user);
			
			user.setAddress(address);
			session.save(user);
			System.out.println("Saving user" + i);
		}
	}
	
	@Override
	public UserDetails getUserById(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from UserDetails where id = :id").setParameter("id", id);
		return (UserDetails) query.getResultList().get(0);
		
	}
	
	@Override
	public UserDetails getUserFromSession(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (UserDetails) currentSession.get(UserDetails.class, id);
	}

	@Override
	public List<UserDetails> getAllUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from UserDetails");
		return (List<UserDetails>) query.getResultList();
	}
	
	@Override
	public int countUsers() {
		String sql = "SELECT COUNT(*) FROM USER_DETAILS";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public UserDetails loadUserFromSession(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		if  (currentSession.isOpen()) {
			return (UserDetails) currentSession.load(UserDetails.class, id);	
		} else {
			System.out.println("The current Session is not open");
		}
		return null;
		
	}
}