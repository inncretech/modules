package com.inncretech.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IdEntry {
  
  private Long id;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  public Long getId(){
    return id;
  }

}
