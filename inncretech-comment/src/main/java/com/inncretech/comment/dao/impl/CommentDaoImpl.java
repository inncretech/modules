package com.inncretech.comment.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;

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
    Query q = getQuery(getIdGenService().getShardId(sourceId, ShardType.SOURCE), "from comment where sourceId = ?");
    q.setParameter(0, sourceId);
    return q.list();
  }
}
