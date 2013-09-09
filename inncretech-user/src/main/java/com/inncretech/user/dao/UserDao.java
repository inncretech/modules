package com.inncretech.user.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.User;

@Component
public class UserDao extends AbstractShardAwareHibernateDao<User> {

  public UserDao() {
    super(User.class, ShardType.USER);
  }

  public void UpdateUserDetails(User obj) {
    getCurrentSession(obj.getId()).saveOrUpdate(obj);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public User createUser(User userDB) {
    getCurrentSession(userDB.getId()).save(userDB);
    return userDB;
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public User getUser(Long userID) {
    Query q = getCurrentSession(userID).createQuery("from User where id = ?");
    q.setLong(0, userID);
    return (User) q.uniqueResult();
  }

  // Assuming that only one user will exists for a given email
  @ShardAware(shardStrategy = "shardid")
  public User getUser(Integer shardId, String emailID) {
    Session sess = getCurrentSessionByShard(shardId);
    Query query = sess.createQuery("from User where email= :email_id").setParameter("email_id", emailID);
    return (User) query.uniqueResult();
  }

}
