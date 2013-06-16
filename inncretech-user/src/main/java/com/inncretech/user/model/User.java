package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.IdEntity;
import com.inncretech.core.model.ShardEntity;



@Entity
public class User  implements ShardEntity{

	private String email;
	  private String userName;
	  private String firstName;
	  private String lastName;
	  private String middleName;
	  private Long id;
	  
	  @Transient
	  public Long getShardedColumnValue(){
	    return this.id;
	  }
	 
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
	  @Column
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	 @Column
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	 @Column
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
			  
		
}
