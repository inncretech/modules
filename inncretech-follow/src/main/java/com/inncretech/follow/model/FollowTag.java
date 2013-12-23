package com.inncretech.follow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class FollowTag extends AbstractMutableEntity {

  @Id
  @Column
	private Long id;

  @Column
	private Long tagId;

  @Column
	private Long followerId;  // sharded on user id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((followerId == null) ? 0 : followerId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
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
    FollowTag other = (FollowTag) obj;
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
    if (tagId == null) {
      if (other.tagId != null)
        return false;
    } else if (!tagId.equals(other.tagId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "FollowTag [id=" + id + ", tagId=" + tagId + ", followerId=" + followerId + ", toString()=" + super.toString() + "]";
  }
}
