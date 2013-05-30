package com.inncretech.user.dao;

import java.util.List;

import org.hibernate.Query;

import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;

public class UserProfileDao extends AbstractShardAwareHibernateDao<User> {

  public UserProfileDao() {
    super(UserProfile.class, ShardType.USER);
  }

  public UserProfile getProfileForUser(Long userId) {
    Query q = getCurrentSession(userId).createQuery("from UserProfile where userId = ?");
    q.setLong(0, userId);
    return  (UserProfile)q.uniqueResult();
  }

}