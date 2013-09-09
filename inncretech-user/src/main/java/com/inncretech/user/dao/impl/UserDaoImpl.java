package com.inncretech.user.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;

@Component
public class UserDaoImpl extends GenericUserShardDaoImpl<User, Long> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }

  /**
   * Assuming that only one user will exists for a given email
   */
  @ShardAware(shardStrategy = "shardid")
  public User getUser(Integer shardId, String emailID) {
    Session sess = getCurrentSessionByShard(shardId);
    Query query = sess.createQuery("from User where email= :email_id").setParameter("email_id", emailID);
    return (User) query.uniqueResult();
  }
}
