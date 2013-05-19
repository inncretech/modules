package com.inncretech.user.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.user.model.User;

@Component
public class UserDao extends AbstractShardAwareHibernateDao<User> {

  public UserDao() {
    super(User.class);
  }

  @ShardAware(shardStrategy = "entityid")
  public User createUser(Long id, User obj) {
    getCurrentSession(id).save(obj);
    return obj;
  }

}
