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
	
}
