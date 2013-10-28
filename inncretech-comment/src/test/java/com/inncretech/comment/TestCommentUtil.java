package com.inncretech.comment;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;

@Component
public class TestCommentUtil extends GenericSourceShardDaoImpl<Comment, Long> {
  public TestCommentUtil() {
    super(Comment.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Long getFirstCommentId(Long sourceId) {
    Query q = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE) , "select id from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    // return (Long) q.list().get(0);
    return (!q.list().isEmpty()) ? (Long) q.list().get(0) : null;

  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Long getLastEnteredCommentId(Long sourceId) {
    Query q = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE) ,"select id from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    // return (Long) q.list().get(q.list().size()-1);
    return (!q.list().isEmpty()) ? (Long) q.list().get(q.list().size() - 1) : null;

  }
}
