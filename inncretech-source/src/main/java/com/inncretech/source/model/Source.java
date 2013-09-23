package com.inncretech.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractImmutatableEntity;
import com.inncretech.core.model.IdEntity;
import com.inncretech.core.model.ShardEntity;


@Entity
public class Source extends AbstractImmutatableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private String sourceUri;

  @Column
  private int sourceType = 0;
  
  @Transient
  public Long getShardedColumnValue(){
    return this.id;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getSourceUri() {
    return sourceUri;
  }

  public void setSourceUri(String sourceUri) {
    this.sourceUri = sourceUri;
  }


  public int getSourceType() {
    return sourceType;
  }

  public void setSourceType(int sourceType) {
    this.sourceType = sourceType;
  }
}
