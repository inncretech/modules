package com.inncretech.like.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.like.model.Like;
import com.inncretech.like.model.UserLike;

public class UserLikeDao extends AbstractShardAwareHibernateDao<Like>{
  
  @Autowired
  private ObjectLikeDao objectLikeDao;
  
  public UserLikeDao(){
    super(Like.class, ShardType.SOURCE);
  }
  
  public List<Like> getAllLikes(Long userId){
    Query q =  getCurrentSession(userId).createQuery("from UserLike where userId = ?");
    q.setParameter(1, userId);
    List<UserLike> userLikes = q.list();
    return objectLikeDao.getAllLikes(new ArrayList<Long>());
    
  }

}
