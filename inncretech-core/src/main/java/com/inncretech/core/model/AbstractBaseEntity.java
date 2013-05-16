package com.inncretech.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.joda.time.DateTime;

public class AbstractBaseEntity implements Serializable{
  
  private Long id;
  private DateTime createdAt;
  private DateTime updatedAt;
  
  @Id
  @Column
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  @Column
  public DateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }
  
  @Column
  public DateTime getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
