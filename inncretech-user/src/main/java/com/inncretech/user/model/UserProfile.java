package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.IdEntity;
import com.inncretech.core.model.ShardEntity;

@Entity
public class UserProfile implements ShardEntity {

  private Long userId;
  private String shortBio;
  private String longBio;

  private Long id;
  
  @Transient
  public Long getShardedColumnValue(){
    return this.userId;
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
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column
  public String getShortBio() {
    return shortBio;
  }

  public void setShortBio(String shortBio) {
    this.shortBio = shortBio;
  }

  @Column
  public String getLongBio() {
    return longBio;
  }

  public void setLongBio(String longBio) {
    this.longBio = longBio;
  }

  @Column
  public String getCurrentAddress() {
    return currentAddress;
  }

  public void setCurrentAddress(String currentAddress) {
    this.currentAddress = currentAddress;
  }

  private String currentAddress;

}
