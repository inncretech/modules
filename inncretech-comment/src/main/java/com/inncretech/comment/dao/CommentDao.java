package com.inncretech.comment.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;

@Component
public class CommentDao extends AbstractShardAwareHibernateDao<Comment> {

  public CommentDao() {
    super(Comment.class, ShardType.SOURCE);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Comment createComment(Long sourceId, Comment obj) {
    getCurrentSession(sourceId).save(obj);
    return obj;
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Comment> getComments(Long sourceId) {
    Query q = getCurrentSession(sourceId).createQuery("from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    return q.list();
  }

}
