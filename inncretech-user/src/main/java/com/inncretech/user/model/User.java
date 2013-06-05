package com.inncretech.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractEntity;

@Entity(name = "user")
public class User {

  private String email;
  private String userName;
  private String firstName;
  private String lastName;
  private String middleName;
  private Long id;
  public User(String FirstName,String LastName,String Email ,String un)
  {
	  this.firstName =FirstName;
	  this.lastName = LastName;
	  this.email = Email;
	  this.userName = un;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
  public String getUserName() {
    return userName;
  }
  
  public String getFName() {
    return firstName;
  }
  public String getLName() {
	    return lastName;
	  }
  public String getMName() {
	    return middleName;
	  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public void setFName(String FName) {
	    this.firstName = FName;
	  }
  public void setLName(String LName) {
	    this.lastName = LName;
	  }
  public void setMName(String MName) {
	    this.middleName = MName;
	  }

}
