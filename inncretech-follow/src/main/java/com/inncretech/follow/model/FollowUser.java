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
	private Long userId; // sharded on userId

	@Column
	private Long followerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

}
