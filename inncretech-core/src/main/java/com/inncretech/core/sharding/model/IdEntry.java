package com.inncretech.core.sharding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractEntity;

@Entity
public class IdEntry extends AbstractEntity {
  
private Long id;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  public Long getId() {
    return id;
  }
  
  public void setId(Long id){
    this.id = id;
  }

}
