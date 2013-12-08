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
  private Byte likeValue;
  
  @Transient
  public Long getShardedColumnValue(){
    return this.objectId;
  }
  
  public Long getId(){
    return this.id;
  }
  
  public void setId(Long id){
    this.id= id;
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
  public Byte getLikeValue() {
    return likeValue;
  }

  public void setLikeValue(Byte likeValue) {
    this.likeValue = likeValue;
  }

}
