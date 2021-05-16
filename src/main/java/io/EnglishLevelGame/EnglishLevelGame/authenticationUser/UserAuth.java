package io.EnglishLevelGame.EnglishLevelGame.authenticationUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.EnglishLevelGame.EnglishLevelGame.security.ApplicationUserRole;


@Entity
@Table(name = "UserAuthentication")
public class UserAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String username;
	
	private String password;
	
	@Transient
    private String passwordConfirm;
	
	private ApplicationUserRole role;
	
	private String firstName;
	
	private String lastName;
	
	private String mail;
	
	private String phoneNumber;
	
	

	public UserAuth() {
	}

	public UserAuth(String username, String password, ApplicationUserRole role, String firstName, String lastName,
			String mail, String phoneNumber, String passwordConfirm) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.passwordConfirm = passwordConfirm;
	}

	public Long getId() {
		return Id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public ApplicationUserRole getRole() {
		return role;
	}

	public void setRole(ApplicationUserRole role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
