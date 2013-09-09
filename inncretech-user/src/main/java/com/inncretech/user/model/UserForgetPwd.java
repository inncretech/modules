package com.inncretech.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

@Entity
public class UserForgetPwd implements ShardEntity {

  private Long userId;
  private String rndString;
  private Date dateRndString;

  private Long id;

  @Transient
  public Long getShardedColumnValue() {
    return this.userId;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column
  public String getRndString() {
    return rndString;
  }

  public void setRndString(String rndString) {
    this.rndString = rndString;
  }

  @Column
  public Date getDate() {
    return dateRndString;
  }

  public void setDate(Date dateRndString) {
    this.dateRndString = dateRndString;
  }
}
