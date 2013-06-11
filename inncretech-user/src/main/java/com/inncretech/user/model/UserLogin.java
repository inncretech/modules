package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;


import com.inncretech.core.model.IdEntity;

@Entity
public class UserLogin implements IdEntity {

  private Long id;

  @Id
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private Long userId;
  private String password;
  private String facebookId;
  private String twitterId;
  private String googleId;
  private Boolean isPasswordloginEnabled;
  private Boolean isFacebookLoginEnabled;
  private Boolean isTwitterLoginEnabled;
  private Boolean isGoogleLoginEnabled;

  @Column
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column
  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  @Column
  public String getTwitterId() {
    return twitterId;
  }

  public void setTwitterId(String twitterId) {
    this.twitterId = twitterId;
  }

  @Column
  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  @Column
  public Boolean getIsPasswordloginEnabled() {
    return isPasswordloginEnabled;
  }

  public void setIsPasswordloginEnabled(Boolean isPasswordloginEnabled) {
    this.isPasswordloginEnabled = isPasswordloginEnabled;
  }

  @Column
  public Boolean getIsFacebookLoginEnabled() {
    return isFacebookLoginEnabled;
  }

  public void setIsFacebookLoginEnabled(Boolean isFacebookLoginEnabled) {
    this.isFacebookLoginEnabled = isFacebookLoginEnabled;
  }

  @Column
  public Boolean getIsTwitterLoginEnabled() {
    return isTwitterLoginEnabled;
  }

  public void setIsTwitterLoginEnabled(Boolean isTwitterLoginEnabled) {
    this.isTwitterLoginEnabled = isTwitterLoginEnabled;
  }

  @Column
  public Boolean getIsGoogleLoginEnabled() {
    return isGoogleLoginEnabled;
  }

  public void setIsGoogleLoginEnabled(Boolean isGoogleLoginEnabled) {
    this.isGoogleLoginEnabled = isGoogleLoginEnabled;
  }

}
