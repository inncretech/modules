package com.inncretech.user.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.User;

@Component
public class UserDao extends AbstractShardAwareHibernateDao<User>{
  
  public UserDao(){
    super(User.class, ShardType.USER);
  }


 
  @ShardAware(shardStrategy = "entityid")
  public void UpdateUserDetails(User obj) {
    getCurrentSession(obj.getId()).save(obj);
  }
  
  
  @ShardAware(shardStrategy = "entityid")
  public User createUser(Long UserID,User userDB) {
	 getCurrentSession(userDB.getId()).save(userDB);
	    return   userDB;
  }
}
