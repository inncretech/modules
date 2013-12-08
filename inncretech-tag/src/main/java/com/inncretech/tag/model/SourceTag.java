package com.inncretech.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.inncretech.core.model.BaseEntity;

/**
 * 
 * communicates with masterdb and should not fall under Shard aware annotation
 */
@Entity
public class SourceTag extends BaseEntity{

  @Id
  @Column
	private Long id;

  @Column
	private Long sourceId;

  @Column
	private Long userId;

  @Column
	private Long tagId;

  @Column
	private byte recordStatus;


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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public byte getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(byte recordStatus) {
		this.recordStatus = recordStatus;
	}

}
