package com.inncretech.comment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.ShardEntity;

@Entity(name = "Comment")
public class Comment implements ShardEntity {
    private Long id;
	private Long sourceId;
	private Long createdBy;
	private String commentText;
	private Long commentParentId;
	private Date createdAt;
	
	@Transient
	private List <Comment> childComments = new ArrayList<Comment>();

	@Transient
	public List<Comment> getChildComments() {
		return childComments;
	}
	public void setChildComments(List<Comment> childComments) {
		this.childComments = childComments;
	}
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
		this.createdBy = userId;
		this.commentText = comment;
		this.commentParentId = commentParentId;
		this.createdAt = commentDate;
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
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Column
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Column
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column
	public Long getCommentParentId() {
		return commentParentId;
	}
	public void setCommentParentId(Long commentParentId) {
		this.commentParentId = commentParentId;
	}
	

}
