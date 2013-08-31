package com.inncretech.core.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@MappedSuperclass
public class AbstractImmutatableEntity extends BaseEntity {

  private DateTime createdAt;

  private Long createdBy;

  /**
   * Return the value associated with the column: createdAt.
   * 
   * @return A DateTime object (this.createdAt)
   */
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  @Basic(optional = true)
  @Column(name = "created_at")
  public DateTime getCreatedAt() {
    return this.createdAt;

  }

  /**
   * Set the value related to the column: createdAt.
   * 
   * @param createdAt
   *          the createdAt value you wish to set
   */
  public void setCreatedAt(final DateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Return the value associated with the column: createdBy.
   * 
   * @return A Long object (this.createdBy)
   */
  @Basic(optional = true)
  @Column(name = "created_by")
  public Long getCreatedBy() {
    return this.createdBy;

  }

  /**
   * Set the value related to the column: createdBy.
   * 
   * @param createdBy
   *          the createdBy value you wish to set
   */
  public void setCreatedBy(final Long createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
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
    AbstractImmutatableEntity other = (AbstractImmutatableEntity) obj;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return super.toString() + " [AbstractImmutatableEntity [createdAt=" + createdAt + ", createdBy=" + createdBy;
  }
}
