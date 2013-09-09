package com.inncretech.user.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.model.UserProfile;

@Component
public class UserProfileDaoImpl extends GenericUserShardDaoImpl<UserProfile, Long> implements UserProfileDao {

  public UserProfileDaoImpl() {
    super(UserProfile.class);
  }

  public UserProfile getProfileForUser(Long userId) {
    Query q = getSession(userId).createQuery("from UserProfile where userId = ?");
    q.setLong(0, userId);
    return (UserProfile) q.uniqueResult();
  }

  @ShardAware(shardStrategy = "entityid")
  public UserProfile CreateUserProfile(UserProfile obj) {
    getSession(obj.getId()).save(obj);
    return obj;
  }
}