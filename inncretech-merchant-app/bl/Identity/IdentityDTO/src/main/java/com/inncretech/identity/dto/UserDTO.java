package com.inncretech.identity.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 691834121780633006L;

	private Long userId;

	@NotNull(message = "user name is compulsory")
	private String userName;

	@NotNull(message = "password is compulsory")
	private String password;

	@NotNull(message = "emailId is compulsory")
	@Email
	private String email;

	private String firstName;

	private String lastName;

	private Boolean isActive;

	@Valid
	private List<RoleDTO> roles;

	private UserIdentifier userIdentifier;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public UserIdentifier getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(UserIdentifier userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", isActive=" + isActive + ", roles=" + roles
				+ ", userIdentifier=" + userIdentifier + "]";
	}

}