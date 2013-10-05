package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class UserForgotPassword extends AbstractMutableEntity{

  @Id
  @Column
  private Long id;

  @Column
  private Long userId;

  @Column
  private String rndString;

  @Type(type="myDateTime")
  @Column
  private DateTime dateRndString;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getRndString() {
    return rndString;
  }

  public void setRndString(String rndString) {
    this.rndString = rndString;
  }

  public DateTime getDateRndString() {
    return dateRndString;
  }

  public void setDateRndString(DateTime dateRndString) {
    this.dateRndString = dateRndString;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((dateRndString == null) ? 0 : dateRndString.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((rndString == null) ? 0 : rndString.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
    UserForgotPassword other = (UserForgotPassword) obj;
    if (dateRndString == null) {
      if (other.dateRndString != null)
        return false;
    } else if (!dateRndString.equals(other.dateRndString))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (rndString == null) {
      if (other.rndString != null)
        return false;
    } else if (!rndString.equals(other.rndString))
      return false;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "UserForgotPassword [id=" + id + ", userId=" + userId + ", rndString=" + rndString + ", dateRndString=" + dateRndString + ", toString()="
        + super.toString() + "]";
  }
}
