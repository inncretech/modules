package com.inncretech.like.model;

import javax.persistence.Entity;

import com.inncretech.core.model.AbstractBaseEntity;

@Entity
public class UserLike extends AbstractBaseEntity {

  private Long userId;
  private Long likeId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getLikeId() {
    return likeId;
  }

  public void setLikeId(Long likeId) {
    this.likeId = likeId;
  }

}
