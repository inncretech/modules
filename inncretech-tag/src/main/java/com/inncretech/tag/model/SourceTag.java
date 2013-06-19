package com.inncretech.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

/**
 * 
 * communicates with masterdb and should not fall under Shard aware annotation
 */
@Entity
public class SourceTag implements ShardEntity{

	private Long id;
	private Long sourceId;
	private Long userId;
	private Long tagId;
	private byte recordStatus;
	
	
	@Transient
	public Long getShardedColumnValue(){
	  return sourceId;
	}

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
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@Column
	public byte getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(byte recordStatus) {
		this.recordStatus = recordStatus;
	}

}
