package com.inncretech.user.dao.impl;


import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.user.dao.UserAccessTokenDao;
import com.inncretech.user.model.UserAccessToken;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccessTokenDaoImpl extends GenericUserShardDaoImpl<UserAccessToken, Long> implements UserAccessTokenDao{

  public UserAccessTokenDaoImpl(){
    super(UserAccessToken.class);
  }

  public UserAccessToken getUserAccessToken(Long userId, String accessToken){
    Query query = getQuery(userId, "from UserAccessToken where userId=:userId and accessToken=:accessToken and recordStatus != :recordStatus");
    query.setParameter("userId", userId);
    query.setParameter("accessToken", accessToken);
    query.setParameter("recordStatus", RecordStatus.INACTIVE);
    UserAccessToken userAccessToken = null;
    List<UserAccessToken> result = query.list();
    if(result.size() > 0){
      userAccessToken = result.get(0);
    }

    return userAccessToken;
  }
}
