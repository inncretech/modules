package com.inncretech.user.dao;

import java.util.List;

import org.hibernate.Query;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.db.model.UserDBEntity;
import com.inncretech.user.db.model.UserLoginDBEntity;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserLogin;
import com.inncretech.user.model.UserProfile;

public class UserLoginDao extends AbstractShardAwareHibernateDao<UserDBEntity> {

  public UserLoginDao() {
    super(UserLogin.class, ShardType.USER);
  }
  @ShardAware(shardStrategy = "entityid")
  public UserLoginDBEntity getLoginDetForUser(Long userId) {
	    Query q = getCurrentSession(userId).createQuery("from UserLogin where userId = ?");
	    q.setLong(0, userId);
	    return  (UserLoginDBEntity)q.uniqueResult();
	  }
  @ShardAware(shardStrategy = "entityid")
  public void UpdateUserLoginDetails(UserLoginDBEntity obj) {
	    getCurrentSession(obj.getId()).save(obj);
	  }
	  

}
