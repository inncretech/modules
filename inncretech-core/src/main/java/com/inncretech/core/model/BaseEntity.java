package com.inncretech.core.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Base class to derive entity classes from.
 * 
 * @author shade05
 */
@MappedSuperclass
public abstract class BaseEntity implements IdEntity, Serializable {

  /**
   * Serialization version ID
   */
  private static final long serialVersionUID = 1L;

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "BaseEntity [id=" + getId() + "]";
  }

  /**
   * Return the type of this class. Useful for when dealing with proxies.
   * 
   * @return Defining class.
   */
  @Transient
  @JsonIgnore
  public Class<?> getClassType() {
    return this.getClass();
  }
}
