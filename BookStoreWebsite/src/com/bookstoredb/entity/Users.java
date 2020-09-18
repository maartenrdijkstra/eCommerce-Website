package com.bookstoredb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

// Generated 17 Sep 2020, 14:52:43 by Hibernate Tools 5.4.18.Final

@Entity
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query =  "SELECT u FROM Users u ORDER BY u.fullName"),
	@NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u")
})
public class Users implements java.io.Serializable {

	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}

	public Users(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
