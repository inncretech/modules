package com.inncretech.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractImmutatableEntity;


@Entity
public class Source extends AbstractImmutatableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private String sourceUri;

  @Column
  private int sourceType = 0;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + sourceType;
    result = prime * result + ((sourceUri == null) ? 0 : sourceUri.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Source other = (Source) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (sourceType != other.sourceType)
      return false;
    if (sourceUri == null) {
      if (other.sourceUri != null)
        return false;
    } else if (!sourceUri.equals(other.sourceUri))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Source [id=" + id + ", sourceUri=" + sourceUri + ", sourceType=" + sourceType + ", toString()=" + super.toString() + "]";
  }
}
