package com.inncretech.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.IdEntity;
import com.inncretech.core.model.ShardEntity;


@Entity
public class Source implements IdEntity, ShardEntity {

  private Long id;

  // <source_type>:<magzine_name>/image_title, product:<mag>
  private String sourceUri;

  private int sourceType = 0;
  
  @Transient
  public Long getShardedColumnValue(){
    return this.id;
  }

  @Id
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  public String getSourceUri() {
    return sourceUri;
  }

  public void setSourceUri(String sourceUri) {
    this.sourceUri = sourceUri;
  }

  @Column
  public int getSourceType() {
    return sourceType;
  }

  public void setSourceType(int sourceType) {
    this.sourceType = sourceType;
  }
}
