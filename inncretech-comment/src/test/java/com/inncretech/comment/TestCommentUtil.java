package com.inncretech.comment;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;

@Component
public class TestCommentUtil extends AbstractShardAwareHibernateDao<Comment> {
  public TestCommentUtil() {
    super(Comment.class, ShardType.SOURCE);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Long getFirstCommentId(Long sourceId) {
    Query q = getCurrentSession(sourceId).createQuery("select id from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    // return (Long) q.list().get(0);
    return (!q.list().isEmpty()) ? (Long) q.list().get(0) : null;

  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Long getLastEnteredCommentId(Long sourceId) {
    Query q = getCurrentSession(sourceId).createQuery("select id from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    // return (Long) q.list().get(q.list().size()-1);
    return (!q.list().isEmpty()) ? (Long) q.list().get(q.list().size() - 1) : null;

  }
}
