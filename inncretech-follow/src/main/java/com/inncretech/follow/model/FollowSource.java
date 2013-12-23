package com.inncretech.follow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class FollowSource extends AbstractMutableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private Long sourceId;

  @Column
  private Long followerId; // shard on user

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  public Long getFollowerId() {
    return followerId;
  }

  public void setFollowerId(Long followerId) {
    this.followerId = followerId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((followerId == null) ? 0 : followerId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
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
    FollowSource other = (FollowSource) obj;
    if (followerId == null) {
      if (other.followerId != null)
        return false;
    } else if (!followerId.equals(other.followerId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (sourceId == null) {
      if (other.sourceId != null)
        return false;
    } else if (!sourceId.equals(other.sourceId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "FollowSource [id=" + id + ", sourceId=" + sourceId + ", followerId=" + followerId + ", toString()=" + super.toString() + "]";
  }
}
