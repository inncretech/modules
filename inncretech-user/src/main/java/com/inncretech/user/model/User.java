package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractEntity;


@Entity
public class User extends AbstractEntity {

  private String email;
  private String userName;
  
  private Long id;

  @Id
  @Column
  public Long getId() {
    return id;
  }
  
  public void setId(Long id){
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

}
