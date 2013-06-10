package com.inncretech.user.dao;



import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;

import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;

@Component
public class UserProfileDao extends AbstractShardAwareHibernateDao<User> {

  public UserProfileDao() {
    super(UserProfile.class, ShardType.USER);
  }

  public UserProfile getProfileForUser(Long userId) {
    Query q = getCurrentSession(userId).createQuery("from UserProfile where userId = ?");
    q.setLong(0, userId);
    return  (UserProfile)q.uniqueResult();
  }
  
  @ShardAware(shardStrategy = "entityid")
  public UserProfile CreateUserProfile(UserProfile obj) {
      getCurrentSession(obj.getId()).save(obj);
      return obj;
    }
       
}