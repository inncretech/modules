package com.inncretech.user.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
import com.inncretech.user.model.UserLogin;

@Component
public class UserFPDao extends AbstractShardAwareHibernateDao<UserForgetPwd> {
  public UserFPDao() {
    super(UserForgetPwd.class, ShardType.USER);
  }
  @ShardAware(shardStrategy = "entityid", shardType=ShardType.USER)
  public UserForgetPwd SetUserPWDLink(UserForgetPwd obj) {
      getCurrentSession(obj.getUserId()).save(obj);
      return obj;
    }


}
