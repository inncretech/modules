package com.inncretech.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.DateTime;

@Embeddable
public class AbstractBaseEntity {

  private DateTime createdAt;
  private DateTime updatedAt;

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
