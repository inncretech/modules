package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.UserAccessToken;

public interface UserAccessTokenDao extends GenericUserShardDAO<UserAccessToken, Long> {

  UserAccessToken getUserAccessToken(Long userId, String accessToken);
}
