package com.inncretech.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.inncretech.core.model.AbstractImmutatableEntity;

@Entity
@Component
public class Tag extends AbstractImmutatableEntity {

  private String name;

  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
