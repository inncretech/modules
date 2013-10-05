package com.inncretech.core.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;


@TypeDef(name = "myDateTime", typeClass = org.jadira.usertype.dateandtime.joda.PersistentDateTime.class, parameters = {
  @Parameter(value = "UTC", name = "databaseZone"), @Parameter(value = "UTC", name = "javaZone") })
@MappedSuperclass
public abstract class AbstractImmutatableEntity extends BaseEntity {
	
  /**
   * Serialization version ID 
   */
  private static final long serialVersionUID = 1L;

  @Type(type="myDateTime")
  @Column()
  private DateTime createdAt;

  @Column
  private Long createdBy;

  /**
   * Return the value associated with the column: createdAt.
   * 
   * @return A DateTime object (this.createdAt)
   */
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
