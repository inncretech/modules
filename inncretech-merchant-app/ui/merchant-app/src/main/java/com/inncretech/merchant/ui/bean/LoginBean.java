package com.inncretech.merchant.ui.bean;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579129416878826336L;

	@Size(min = 3, max = 15, message = "User Name Should be greater than 2 and less than 16")
	@NotBlank(message = "UserName Is Compulsory :)")
	private String username;

	@Size(min = 6, max = 30, message = "Password Should be greater than 5 and less than 16")
	@NotBlank(message = "Password Is Compulsory :)")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}