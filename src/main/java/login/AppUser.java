package mipl.icmodule.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "APP_USER")
	public class AppUser {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "ENCRYTED_PASSWORD")
	private String encrytedPassword;
	
	@Column(name = "ENABLED")
	private byte enabled;

	public AppUser() {

	}

	public AppUser(Long userId, String userName, String encrytedPassword) {
	this.userId = userId;
	this.userName = userName;
	this.encrytedPassword = encrytedPassword;
	}

	public Long getUserId() {
	return userId;
	}

	public void setUserId(Long userId) {
	this.userId = userId;
	}

	public String getUserName() {
	return userName;
	}

	public void setUserName(String userName) {
	this.userName = userName;
	}

	public String getEncrytedPassword() {
	return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
	this.encrytedPassword = encrytedPassword;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
	return this.userName + "/" + this.encrytedPassword;
	}

	}