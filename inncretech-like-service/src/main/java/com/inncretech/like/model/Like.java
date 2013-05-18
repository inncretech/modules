package com.inncretech.like.model;

import javax.persistence.Entity;

import com.inncretech.core.model.AbstractBaseEntity;

@Entity
public class Like extends AbstractBaseEntity {
  private Long objectId;
  private Long userId;
  private Byte likeValue;

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

  public Byte getLikeValue() {
    return likeValue;
  }

  public void setLikeValue(Byte likeValue) {
    this.likeValue = likeValue;
  }

}
