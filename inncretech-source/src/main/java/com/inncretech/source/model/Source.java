package com.inncretech.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractImmutatableEntity;

@Entity
public class Source extends AbstractImmutatableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private String sourceUri;

  @Column
  private Double score;

  @Column
  private Integer sourceType;

  @Column
  private Long magazineId;

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

  public Integer getSourceType() {
    return sourceType;
  }

  public void setSourceType(int sourceType) {
    this.sourceType = sourceType;
  }

  public Long getMagazineId() {
    return magazineId;
  }

  public void setMagazineId(Long magazineId) {
    this.magazineId = magazineId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((magazineId == null) ? 0 : magazineId.hashCode());
    result = prime * result + ((score == null) ? 0 : score.hashCode());
    result = prime * result + ((sourceType == null) ? 0 : sourceType.hashCode());
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
    if (magazineId == null) {
      if (other.magazineId != null)
        return false;
    } else if (!magazineId.equals(other.magazineId))
      return false;
    if (score == null) {
      if (other.score != null)
        return false;
    } else if (!score.equals(other.score))
      return false;
    if (sourceType == null) {
      if (other.sourceType != null)
        return false;
    } else if (!sourceType.equals(other.sourceType))
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
    return "Source [id=" + id + ", sourceUri=" + sourceUri + ", score=" + score + ", sourceType=" + sourceType + ", magazineId=" + magazineId
        + ", toString()=" + super.toString() + "]";
  }
}
