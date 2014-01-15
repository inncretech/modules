package com.inncretech.user.model;

import com.inncretech.core.model.AbstractImmutatableEntity;
import com.inncretech.core.model.AbstractMutableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserLoginLookup extends AbstractMutableEntity{

  @Id
  @GeneratedValue
  @Column
  private Long id;

  @Column
  private String login;

  @Column
  private Long userId;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
