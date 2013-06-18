package com.inncretech.comment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

@Entity(name = "Comment")
public class Comment implements ShardEntity {
    private Long id;
	private Long sourceId;
	private Long userId;
	private String comment;
	private Long commentParentId;
	private Date commentDate;

	public Comment(){
		
	}
	@Transient
	public Long getShardedColumnValue(){
	  return this.sourceId;
	}
	
	public Comment(Long id, Long sourceId, Long userId, String comment,
			Long commentParentId, Date commentDate) {
		super();
		this.id = id;
		this.sourceId = sourceId;
		this.userId = userId;
		this.comment = comment;
		this.commentParentId = commentParentId;
		this.commentDate = commentDate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column
	public Long getCommentParentId() {
		return commentParentId;
	}
	public void setCommentParentId(Long commentParentId) {
		this.commentParentId = commentParentId;
	}
	
	@Column
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
		
}
