package com.fdmgroup.HappyNews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="happy_users")
public class HappyUser {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="username")
	private String username;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	
	public HappyUser() {
		super();
	}
	
	public HappyUser(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = hashingPassword(password);
	}

	public HappyUser(String username, String email, String password, String role) {
		
		this.username = username;
		this.email = email;
		this.password = hashingPassword(password);
		this.role = role;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = hashingPassword(password);
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "HappyUser [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}

	public String hashingPassword(String password) {
		Integer hashedPassword = password.hashCode();
		return hashedPassword.toString();
		
				
	}
	
	
}
