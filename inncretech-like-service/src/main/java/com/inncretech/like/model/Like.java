package com.inncretech.like.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractBaseEntity;
import com.inncretech.core.model.IdEntity;;

@Entity
public class Like implements IdEntity {
  private Long id;
  private Long objectId;
  private Long userId;
  private Byte likeValue;
  
  @Column
  @Id
  public Long getId(){
    return this.id;
  }
  
  public void setId(Long id){
    this.id= id;
  }
  @Column
  public Long getObjectId() {
    return objectId;
  }

  public void setObjectId(Long objectId) {
    this.objectId = objectId;
  }
  @Column
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
  @Column
  public Byte getLikeValue() {
    return likeValue;
  }

  public void setLikeValue(Byte likeValue) {
    this.likeValue = likeValue;
  }

}
