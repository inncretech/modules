package com.inncretech.user.model;

import com.inncretech.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserForgotPasswordLookup extends BaseEntity{

  @Id
  @GeneratedValue
  @Column
  private Long id;

  @Column
  private String fortgotPasswordKey;

  @Column
  private Long userId;

  public String getFortgotPasswordKey() {
    return fortgotPasswordKey;
  }

  public void setFortgotPasswordKey(String fortgotPasswordKey) {
    this.fortgotPasswordKey = fortgotPasswordKey;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
