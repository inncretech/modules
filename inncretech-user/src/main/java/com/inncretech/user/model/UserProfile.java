package com.inncretech.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractMutableEntity;
import com.inncretech.core.model.ShardEntity;

@Entity
public class UserProfile extends AbstractMutableEntity implements ShardEntity {

  @Column
  private Long userId;

  @Column
  private String shortBio;

  @Column
  private String longBio;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Transient
  public Long getShardedColumnValue() {
    return this.userId;
  }

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

  public String getShortBio() {
    return shortBio;
  }

  public void setShortBio(String shortBio) {
    this.shortBio = shortBio;
  }

  public String getLongBio() {
    return longBio;
  }

  public void setLongBio(String longBio) {
    this.longBio = longBio;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((longBio == null) ? 0 : longBio.hashCode());
    result = prime * result + ((shortBio == null) ? 0 : shortBio.hashCode());
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
    UserProfile other = (UserProfile) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (longBio == null) {
      if (other.longBio != null)
        return false;
    } else if (!longBio.equals(other.longBio))
      return false;
    if (shortBio == null) {
      if (other.shortBio != null)
        return false;
    } else if (!shortBio.equals(other.shortBio))
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
    return "UserProfile [userId=" + userId + ", shortBio=" + shortBio + ", longBio=" + longBio + ", id=" + id + ", toString()=" + super.toString()
        + "]";
  }
}
