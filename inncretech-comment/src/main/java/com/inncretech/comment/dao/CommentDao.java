package com.inncretech.comment.dao;

import java.util.List;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericSourceShardDAO;

public interface CommentDao extends GenericSourceShardDAO<Comment, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Comment createComment(Long sourceId, Comment obj);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Comment> getComments(Long sourceId);
}
