package com.inncretech.follow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

@Entity
public class FollowSource implements ShardEntity {

	private Long id;
	private Long sourceId;
	private Long followerId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	@Column
	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	@Transient
	public Long getShardedColumnValue() {
		return sourceId;
	}

}
