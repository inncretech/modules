package com.inncretech.user.dao;

import java.util.List;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.GenericUserShardDAO;
import com.inncretech.user.model.User;

@Component
public interface UserDao extends GenericUserShardDAO<User, Long> {

  public void activateUser(User user);

  List<User> getMatchingUsers(String pattern, boolean exactMatch, boolean startWith);

  @ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
  List<User> getUsersInShard(Integer shardId, Integer offset , Integer pageSize);

}
