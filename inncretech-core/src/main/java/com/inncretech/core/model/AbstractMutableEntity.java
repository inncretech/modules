package com.inncretech.core.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;

@TypeDef(name = "myDateTime", typeClass = org.jadira.usertype.dateandtime.joda.PersistentDateTime.class, parameters = {
    @Parameter(value = "UTC", name = "databaseZone"), @Parameter(value = "UTC", name = "javaZone") })
@MappedSuperclass
public abstract class AbstractMutableEntity extends AbstractImmutatableEntity {

  /**
   * Serialization version ID 
   */
  private static final long serialVersionUID = 1L;
	
  @Type(type="myDateTime")
  @Basic(optional = false)
  @Column(name = "updated_at", nullable = false)
  private DateTime updatedAt;
  
  @Column
  private Long updatedBy;
  
  @Version
  private Long versionId;
  
  @Column
  private Byte recordStatus;

  public AbstractMutableEntity(){
    setRecordStatus(RecordStatus.ACTIVE.getId());
  }

  /**
   * Return the value associated with the column: updatedAt.
   * 
   * @return A DateTime object (this.updatedAt)
   */
  public DateTime getUpdatedAt() {
    return this.updatedAt;

  }

  /**
   * Set the value related to the column: updatedAt.
   * 
   * @param updatedAt
   *          the updatedAt value you wish to set
   */
  public void setUpdatedAt(final DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * Return the value associated with the column: updatedBy.
   * 
   * @return A Long object (this.updatedBy)
   */
  public Long getUpdatedBy() {
    return this.updatedBy;

  }

  /**
   * Set the value related to the column: updatedBy.
   * 
   * @param updatedBy
   *          the updatedBy value you wish to set
   */
  public void setUpdatedBy(final Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  /**
   * Return the value associated with the column: versionId.
   * 
   * @return A Long object (this.versionId)
   */
  public Long getVersionId() {
    return this.versionId;

  }

  /**
   * Set the value related to the column: versionId.
   * 
   * @param versionId
   *          the versionId value you wish to set
   */
  public void setVersionId(final Long versionId) {
    this.versionId = versionId;
  }

  /**
   * Return the value associated with the column: recordStatus.
   * 
   * @return A Byte object (this.recordStatus)
   */
  @Column(name = "record_status")
  public Byte getRecordStatus() {
    return this.recordStatus;

  }

  /**
   * Set the value related to the column: recordStatus.
   * 
   * @param recordStatus
   *          the recordStatus value you wish to set
   */
  public void setRecordStatus(final Byte recordStatus) {
    this.recordStatus = recordStatus;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((recordStatus == null) ? 0 : recordStatus.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
    AbstractMutableEntity other = (AbstractMutableEntity) obj;
    if (recordStatus == null) {
      if (other.recordStatus != null)
        return false;
    } else if (!recordStatus.equals(other.recordStatus))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    if (updatedBy == null) {
      if (other.updatedBy != null)
        return false;
    } else if (!updatedBy.equals(other.updatedBy))
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
    return "AbstractMutableEntity [updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + ", versionId=" + versionId + ", recordStatus="
        + recordStatus + ", toString()=" + super.toString() + "]";
  }

}
