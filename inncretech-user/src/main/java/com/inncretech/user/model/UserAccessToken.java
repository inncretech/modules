package com.inncretech.user.model;

import com.inncretech.core.model.AbstractMutableEntity;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccessToken extends AbstractMutableEntity{

  @Id
  @Column
  private Long id;

  @Column
  private Long userId;

  @Column
  private String accessToken;

  @Column
  private String tokenKey;

  @Column
  private String deviceId;

  @Type(type="myDateTime")
  @Basic(optional = false)
  @Column
  private DateTime expiryAt;

  public String getTokenKey() {
    return tokenKey;
  }

  public void setTokenKey(String tokenKey) {
    this.tokenKey = tokenKey;
  }

  public DateTime getExpiryAt() {
    return expiryAt;
  }

  public void setExpiryAt(DateTime expiryAt) {
    this.expiryAt = expiryAt;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
