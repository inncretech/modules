package com.inncretech.comment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;

@Component
public class CommentDao extends AbstractShardAwareHibernateDao<Comment>{
  
	  @Autowired
	  private SessionFactory sessionFactory;
	  
  
	public CommentDao(){
	    super(Comment.class, ShardType.SOURCE);
	  }

	  @ShardAware(shardStrategy = "entityid")
	  public Comment createComment(Long sourceId, Comment obj) {
	    getCurrentSession(sourceId).save(obj);
	    return obj;
	  }
	
	 @ShardAware(shardStrategy = "entityid")
	  public List<Comment> getComments(Long sourceId) {
		 Query q=getCurrentSession(sourceId).createQuery("from Comment where sourceId=?");
		 q.setParameter(0, sourceId);
		 return q.list();
	  }

}
