package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

@Entity
public class User implements ShardEntity {

  private String email;
  private String userName;
  private String firstName;
  private String lastName;
  private String middleName;
  private Long id;
  private String password;
  private String facebookId;
  private String twitterId;
  private String googleId;
  private Boolean isPasswordloginEnabled;
  private Boolean isFacebookLoginEnabled;
  private Boolean isTwitterLoginEnabled;
  private Boolean isGoogleLoginEnabled;

  @Transient
  public Long getShardedColumnValue() {
    return this.id;
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
