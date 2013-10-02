package com.inncretech.core.sharding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inncretech.core.model.IdEntity;

@Entity
public class IdEntry implements IdEntity {

  /**
   * Serialization version ID 
   */
  private static final long serialVersionUID = 1L;
	
  private Long id;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
