package com.im;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String email;
	private Timestamp date;
	private int status;
	private Timestamp authenticationTime;
	private String userKey;
	private String ip;
	private int port;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="date")
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="authenticationTime")
	public Timestamp getAuthenticationTime() {
		return authenticationTime;
	}
	public void setAuthenticationTime(Timestamp authenticationTime) {
		this.authenticationTime = authenticationTime;
	}
	@Column(name="userKey")
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	@Column(name="IP")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name="port")
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
