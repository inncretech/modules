package com.inncretech.comment.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.core.util.DateTimeUtils;

@SuppressWarnings("unchecked")
@Component
public class CommentDaoImpl extends GenericSourceShardDaoImpl<Comment, Long> implements CommentDao {

  public CommentDaoImpl() {
    super(Comment.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Comment createComment(Long sourceId, Comment obj) {
    save(obj);
    return obj;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Comment> getComments(Long sourceId) {
    Query query = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE), 
    		"from comment where sourceId = :sourceId and recordStatus = :recordStatus");
  	query.setParameter("sourceId", sourceId);
		query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    return query.list();
  }
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Comment deleteComment(Long  commentId) {
    
    
    Query query = getQuery(getIdGenService().getShardId(commentId, ShardType.SOURCE),
    		"from comment where id= :commentId"
        + " and recordStatus= :recordStatus");

    query.setParameter("recordStatus", RecordStatus.ACTIVE.getId());
    query.setParameter("commentId", commentId);
    
    Comment comment = (Comment) query.uniqueResult();
    
    comment.setRecordStatus(RecordStatus.INACTIVE.getId());
    comment.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    comment.setUpdatedBy(comment.getCreatedBy());
    update(comment);
    return comment;
  }
}
