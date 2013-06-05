package com.inncretech.user.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.inncretech.core.model.AbstractEntity;



@Entity(name = "userDBEntity")

public class UserDBEntity  extends AbstractEntity{

	 private String email;
	  private String userName;
	  private String firstName;
	  private String lastName;
	  private String middleName;
	  private Long id;
	 
	  @Id
	  @Column	
	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  @Column
	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  @Column
	  public String getUserName() {
	    return userName;
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
