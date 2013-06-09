package com.inncretech.user.dao;


import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.UserLogin;

@Component
public class UserLoginDao extends AbstractShardAwareHibernateDao<UserLogin> {

  public UserLoginDao() {
    super(UserLogin.class, ShardType.USER);
  }
  @ShardAware(shardStrategy = "entityid")
  public UserLogin getLoginDetForUser(Long userId) {
	    Query q = getCurrentSession(userId).createQuery("from UserLogin where userId = ?");
	    q.setLong(0, userId);
	    return  (UserLogin)q.uniqueResult();
	  }
  @ShardAware(shardStrategy = "entityid")
  public void UpdateUserLoginDetails(UserLogin obj) {
	    getCurrentSession(obj.getId()).save(obj);
	  }
	  

}
