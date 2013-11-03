package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class User extends AbstractMutableEntity {

  @NotEmpty
  @Column
  private String email;

  @NotEmpty
  @Column
  private String userName;

  @NotEmpty
  @Column
  private String firstName;

  @NotEmpty
  @Column
  private String lastName;

  @Column
  private String middleName;

  @Id
  private Long id;

  @NotEmpty
  @Column
  private String password;

  @Column
  private String passwordSalt;

  @Column
  private String masterKey;

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  public String getMasterKey() {
    return masterKey;
  }

  public void setMasterKey(String masterKey) {
    this.masterKey = masterKey;
  }

  public Boolean getFacebookLoginEnabled() {
    return isFacebookLoginEnabled;
  }

  public void setFacebookLoginEnabled(Boolean facebookLoginEnabled) {
    isFacebookLoginEnabled = facebookLoginEnabled;
  }

  public Boolean getPasswordloginEnabled() {
    return isPasswordloginEnabled;
  }

  public void setPasswordloginEnabled(Boolean passwordloginEnabled) {
    isPasswordloginEnabled = passwordloginEnabled;
  }

  public Boolean getTwitterLoginEnabled() {
    return isTwitterLoginEnabled;
  }

  public void setTwitterLoginEnabled(Boolean twitterLoginEnabled) {
    isTwitterLoginEnabled = twitterLoginEnabled;
  }

  public Boolean getGoogleLoginEnabled() {
    return isGoogleLoginEnabled;
  }

  public void setGoogleLoginEnabled(Boolean googleLoginEnabled) {
    isGoogleLoginEnabled = googleLoginEnabled;
  }

  @Column

  private String facebookId;

  @Column
  private String twitterId;

  @Column
  private String googleId;

  @Column
  private Boolean isPasswordloginEnabled;

  @Column
  private Boolean isFacebookLoginEnabled;

  @Column
  private Boolean isTwitterLoginEnabled;

  @Column
  private Boolean isGoogleLoginEnabled;

  public Long getShardedColumnValue() {
    return this.id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((facebookId == null) ? 0 : facebookId.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((googleId == null) ? 0 : googleId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((isFacebookLoginEnabled == null) ? 0 : isFacebookLoginEnabled.hashCode());
    result = prime * result + ((isGoogleLoginEnabled == null) ? 0 : isGoogleLoginEnabled.hashCode());
    result = prime * result + ((isPasswordloginEnabled == null) ? 0 : isPasswordloginEnabled.hashCode());
    result = prime * result + ((isTwitterLoginEnabled == null) ? 0 : isTwitterLoginEnabled.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((twitterId == null) ? 0 : twitterId.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (facebookId == null) {
      if (other.facebookId != null)
        return false;
    } else if (!facebookId.equals(other.facebookId))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (googleId == null) {
      if (other.googleId != null)
        return false;
    } else if (!googleId.equals(other.googleId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (isFacebookLoginEnabled == null) {
      if (other.isFacebookLoginEnabled != null)
        return false;
    } else if (!isFacebookLoginEnabled.equals(other.isFacebookLoginEnabled))
      return false;
    if (isGoogleLoginEnabled == null) {
      if (other.isGoogleLoginEnabled != null)
        return false;
    } else if (!isGoogleLoginEnabled.equals(other.isGoogleLoginEnabled))
      return false;
    if (isPasswordloginEnabled == null) {
      if (other.isPasswordloginEnabled != null)
        return false;
    } else if (!isPasswordloginEnabled.equals(other.isPasswordloginEnabled))
      return false;
    if (isTwitterLoginEnabled == null) {
      if (other.isTwitterLoginEnabled != null)
        return false;
    } else if (!isTwitterLoginEnabled.equals(other.isTwitterLoginEnabled))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
        return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (twitterId == null) {
      if (other.twitterId != null)
        return false;
    } else if (!twitterId.equals(other.twitterId))
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [email=" + email + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
        + ", id=" + id + ", password=" + password + ", facebookId=" + facebookId + ", twitterId=" + twitterId + ", googleId=" + googleId
        + ", isPasswordloginEnabled=" + isPasswordloginEnabled + ", isFacebookLoginEnabled=" + isFacebookLoginEnabled + ", isTwitterLoginEnabled="
        + isTwitterLoginEnabled + ", isGoogleLoginEnabled=" + isGoogleLoginEnabled + ", toString()=" + super.toString() + "]";
  }
}
