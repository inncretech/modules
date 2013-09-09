package com.inncretech.user.dao;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.UserForgetPwd;

public interface UserFPDao extends GenericUserShardDAO<UserForgetPwd, Long> {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public UserForgetPwd getDateForRandomString(UserForgetPwd obj);
}
