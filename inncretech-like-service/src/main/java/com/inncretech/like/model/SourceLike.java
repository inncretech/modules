package com.inncretech.like.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractImmutatableEntity;

@Entity
public class SourceLike extends AbstractImmutatableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private Long objectId;

  @Column
  private Long userId;

  @Column
  private Integer likeValue;

  @Transient
  public Long getShardedColumnValue() {
    return this.objectId;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getObjectId() {
    return objectId;
  }

  public void setObjectId(Long objectId) {
    this.objectId = objectId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getLikeValue() {
    return likeValue;
  }

  public void setLikeValue(Integer likeValue) {
    this.likeValue = likeValue;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((likeValue == null) ? 0 : likeValue.hashCode());
    result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
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
    SourceLike other = (SourceLike) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (likeValue == null) {
      if (other.likeValue != null)
        return false;
    } else if (!likeValue.equals(other.likeValue))
      return false;
    if (objectId == null) {
      if (other.objectId != null)
        return false;
    } else if (!objectId.equals(other.objectId))
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
    return "SourceLike [id=" + id + ", objectId=" + objectId + ", userId=" + userId + ", likeValue=" + likeValue + ", toString()=" + super.toString()
        + "]";
  }
}
