package com.inncretech.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.inncretech.core.model.BaseEntity;

@Entity
@SQLDelete(sql = "UPDATE source SET record_status = 0, version_id = version_id + 1 where id = ? and version_id = ?")
public class Source extends BaseEntity {

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

  @Version
  private Long versionId;

  @Column
  private Byte recordStatus;

  @Type(type = "updatedTime")
  private DateTime updatedAt;

  @Type(type = "updatedTime")
  @Column()
  private DateTime createdAt;

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

  public Long getVersionId() {
    return versionId;
  }

  public void setVersionId(Long versionId) {
    this.versionId = versionId;
  }

  public Byte getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(Byte recordStatus) {
    this.recordStatus = recordStatus;
  }

  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((magazineId == null) ? 0 : magazineId.hashCode());
    result = prime * result + ((recordStatus == null) ? 0 : recordStatus.hashCode());
    result = prime * result + ((score == null) ? 0 : score.hashCode());
    result = prime * result + ((sourceType == null) ? 0 : sourceType.hashCode());
    result = prime * result + ((sourceUri == null) ? 0 : sourceUri.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((versionId == null) ? 0 : versionId.hashCode());
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
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
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
    if (recordStatus == null) {
      if (other.recordStatus != null)
        return false;
    } else if (!recordStatus.equals(other.recordStatus))
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
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    if (versionId == null) {
      if (other.versionId != null)
        return false;
    } else if (!versionId.equals(other.versionId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Source [id=" + id + ", sourceUri=" + sourceUri + ", score=" + score + ", sourceType=" + sourceType + ", magazineId=" + magazineId
        + ", versionId=" + versionId + ", recordStatus=" + recordStatus + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
  }
}
