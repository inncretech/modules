package com.inncretech.follow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class FollowUser extends AbstractMutableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private Long followerUserId; // sharded on userId

  @Column
  private Long followedUserId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFollowerUserId() {
    return followerUserId;
  }

  public void setFollowerUserId(Long followerUserId) {
    this.followerUserId = followerUserId;
  }

  public Long getFollowedUserId() {
    return followedUserId;
  }

  public void setFollowedUserId(Long followedUserId) {
    this.followedUserId = followedUserId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((followedUserId == null) ? 0 : followedUserId.hashCode());
    result = prime * result + ((followerUserId == null) ? 0 : followerUserId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    FollowUser other = (FollowUser) obj;
    if (followedUserId == null) {
      if (other.followedUserId != null)
        return false;
    } else if (!followedUserId.equals(other.followedUserId))
      return false;
    if (followerUserId == null) {
      if (other.followerUserId != null)
        return false;
    } else if (!followerUserId.equals(other.followerUserId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "FollowUser [id=" + id + ", followerUserId=" + followerUserId + ", followedUserId=" + followedUserId + ", toString()=" + super.toString()
        + "]";
  }
}
