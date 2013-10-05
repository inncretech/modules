package com.inncretech.comment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.inncretech.core.model.AbstractImmutatableEntity;
import com.inncretech.core.model.ShardEntity;

@Entity(name = "comment")
public class Comment extends AbstractImmutatableEntity {

  @Id
  @Column
  private Long id;

  @Column
  private Long sourceId;

  @Column
  private String commentText;

  @Column
  private Long commentParentId;

  @Transient
  private List<Comment> childComments = new ArrayList<Comment>();

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

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public Long getCommentParentId() {
    return commentParentId;
  }

  public void setCommentParentId(Long commentParentId) {
    this.commentParentId = commentParentId;
  }

  public List<Comment> getChildComments() {
    return childComments;
  }

  public void setChildComments(List<Comment> childComments) {
    this.childComments = childComments;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((childComments == null) ? 0 : childComments.hashCode());
    result = prime * result + ((commentParentId == null) ? 0 : commentParentId.hashCode());
    result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
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
    Comment other = (Comment) obj;
    if (childComments == null) {
      if (other.childComments != null)
        return false;
    } else if (!childComments.equals(other.childComments))
      return false;
    if (commentParentId == null) {
      if (other.commentParentId != null)
        return false;
    } else if (!commentParentId.equals(other.commentParentId))
      return false;
    if (commentText == null) {
      if (other.commentText != null)
        return false;
    } else if (!commentText.equals(other.commentText))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (sourceId == null) {
      if (other.sourceId != null)
        return false;
    } else if (!sourceId.equals(other.sourceId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", sourceId=" + sourceId + ", commentText=" + commentText + ", commentParentId=" + commentParentId
        + ", childComments=" + childComments + ", toString()=" + super.toString() + "]";
  }
}
