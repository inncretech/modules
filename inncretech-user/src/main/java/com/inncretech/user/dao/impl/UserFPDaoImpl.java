package com.inncretech.user.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.model.UserForgetPwd;

@Component
public class UserFPDaoImpl extends GenericUserShardDaoImpl<UserForgetPwd, Long> implements UserFPDao {

  public UserFPDaoImpl() {
    super(UserForgetPwd.class);
  }

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public UserForgetPwd getDateForRandomString(UserForgetPwd obj) {
    Query q = getSession(obj.getUserId()).createQuery("from UserForgetPwd where rndString = ?");
    q.setString(0, obj.getRndString());
    return (UserForgetPwd) q.uniqueResult();
  }
}
