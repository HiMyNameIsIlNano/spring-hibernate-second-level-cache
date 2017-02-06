package com.daniele.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "USER_DETAILS")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="user")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name="USER_NAME")	
	String name;
	
	@Temporal(TemporalType.DATE)
	Date joinDate;
	
	@Lob
	String description;
	
	@Transient
	String uselessField;
	
	@OneToOne(mappedBy = "user")
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Address address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUselessField() {
		return uselessField;
	}

	public void setUselessField(String uselessField) {
		this.uselessField = uselessField;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " Join Date: " + joinDate + " Description: " + description;
	}
}
