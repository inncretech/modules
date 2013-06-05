package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractBaseEntity;
import com.inncretech.core.model.AbstractEntity;

public class UserLogin extends AbstractEntity {

  private Long id;

  
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

  
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  
  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  
  public String getTwitterId() {
    return twitterId;
  }

  public void setTwitterId(String twitterId) {
    this.twitterId = twitterId;
  }

  
  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  
  public Boolean getIsPasswordloginEnabled() {
    return isPasswordloginEnabled;
  }

  public void setIsPasswordloginEnabled(Boolean isPasswordloginEnabled) {
    this.isPasswordloginEnabled = isPasswordloginEnabled;
  }

  
  public Boolean getIsFacebookLoginEnabled() {
    return isFacebookLoginEnabled;
  }

  public void setIsFacebookLoginEnabled(Boolean isFacebookLoginEnabled) {
    this.isFacebookLoginEnabled = isFacebookLoginEnabled;
  }

  
  public Boolean getIsTwitterLoginEnabled() {
    return isTwitterLoginEnabled;
  }

  public void setIsTwitterLoginEnabled(Boolean isTwitterLoginEnabled) {
    this.isTwitterLoginEnabled = isTwitterLoginEnabled;
  }

  
  public Boolean getIsGoogleLoginEnabled() {
    return isGoogleLoginEnabled;
  }

  public void setIsGoogleLoginEnabled(Boolean isGoogleLoginEnabled) {
    this.isGoogleLoginEnabled = isGoogleLoginEnabled;
  }

}
