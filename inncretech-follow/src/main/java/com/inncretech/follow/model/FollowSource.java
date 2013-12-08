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
	


}
