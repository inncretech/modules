package com.inncretech.user.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.User;

@Component
public interface UserDao extends GenericUserShardDAO<User, Long> {
  
  @ShardAware(shardStrategy = "shardid")
  public User getUser(Integer shardId, String emailID);
}
