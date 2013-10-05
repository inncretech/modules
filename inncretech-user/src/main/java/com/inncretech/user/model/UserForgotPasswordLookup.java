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
  private String key;

  @Column
  private Long userId;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
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
