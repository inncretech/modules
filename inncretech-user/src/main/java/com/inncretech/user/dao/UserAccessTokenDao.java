package com.inncretech.user.dao;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.UserAccessToken;

public interface UserAccessTokenDao extends GenericUserShardDAO<UserAccessToken, Long> {

  UserAccessToken getUserAccessToken(Long userId, String accessToken);
  
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void inactivateUserAccessTokens(Long userId);
}
