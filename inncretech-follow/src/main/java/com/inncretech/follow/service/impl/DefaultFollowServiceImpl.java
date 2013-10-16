package com.inncretech.follow.service.impl;

import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;
import com.inncretech.follow.model.FollowUser;
import com.inncretech.follow.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultFollowServiceImpl implements FollowService {

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Autowired
  private HibernateSessionFactoryManager hibernateSessionFactoryManager;

  @Autowired
  private FollowSourceDao followSourceDao;

  @Autowired
  private FollowTagDao followTagDao;

  @Autowired
  private FollowUserDao followUserDao;
  
  @Autowired
  IdGenerator idGenerator;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void followTag(Long followerId, Long tagId) {
    FollowTag followTag = new FollowTag();
    List<FollowTag> followerList = getFollowersByTag(tagId);
    for (int i = 0; i < followerList.size(); i++) {
      Long existingFollowerId = followerList.get(i).getFollowerId().longValue();
      if (followerId.longValue() != existingFollowerId) {

        followTag.setTagId(tagId);
        followTag.setFollowerId(followerId);
        followTagDao.saveFollowTag(followTag);
      }

    }

  }


  @Override
  public List<FollowTag> getFollowersByTag(Long tagId) {

    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
    List<FollowTag> followersList = new ArrayList<FollowTag>();

    for (ShardConfig config : shardConfigs) {
      followersList.addAll(followTagDao.getFollowersByTag(config.getId(), tagId));
    }

    return followersList;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void followSource(Long sourceId, Long followerId) {
    FollowSource followSource = new FollowSource();
    followSource.setFollowerId(followerId);
    followSource.setSourceId(sourceId);

    followSourceDao.saveFollowSource(followSource);

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void followUser(Long userId, Long followerId) {
    
	FollowUser followUser = null;
    List<Object> followerList = getFollowersByUser(userId);
    for (int i = 0; i < followerList.size(); i++) {
    	
      followUser = ((FollowUser)followerList.get(i));
      long existingFollowerId = followUser.getFollowerId().longValue();
  
      if (followerId.longValue() == existingFollowerId) 
    	  return;
      
    }
    followUser = new FollowUser();
    followUser.setFollowerId(followerId);
    followUser.setUserId(userId);
    followUser.setId(idGenerator.getNewIdOnUserShard(userId));
    
    followUserDao.saveFollowUser(followUser);
  }

    @Override
    @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
    public List<Object> getFollowersByUser(Long userId) {
      
      List<Object> followersList = new ArrayList<Object>();      
      followersList.add(followUserDao.getFollowersByUser(idGenerator.getShardId(userId, ShardType.USER),userId));   
      return (followersList.size() > 0) ? followersList : null;
      
    }
  
    @Override
    public List<Object> getFollowedUsers(Long userId) {
      List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
      @SuppressWarnings("unchecked")
      List<Object> followedUsersList = new ArrayList<Object>();

      for (ShardConfig config : shardConfigs) {
        followedUsersList.addAll(followUserDao.getFollowedByUser(config.getId(), userId));
      }

      return (followedUsersList.size() > 0) ? followedUsersList : null;
    }
  
  
  @Override
  public List<Object> getFollowersBySource(Long sourceId) {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
    @SuppressWarnings("unchecked")
    List<Object> sourcesList = new ArrayList<Object>();

    for (ShardConfig config : shardConfigs) {
      sourcesList.addAll(followSourceDao.getFollowersBySource(config.getId(), sourceId));
    }
    System.out.println("Size of Followers List by Source:" + sourcesList.size());

    return (sourcesList.size() > 0) ? sourcesList : null;
  }



  @Override
  public List<Object> getFollowedSources(Long userId) {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
    @SuppressWarnings("unchecked")
    List<Object> sourcesList = new ArrayList<Object>();

    for (ShardConfig config : shardConfigs) {
      sourcesList.addAll(followSourceDao.getFollowedSources(config.getId(), userId));
    }

    return (sourcesList.size() > 0) ? sourcesList : null;
  }



  @Override
  public List<Object> getFollowedTags(Long userId) {
    List<ShardConfig> shardConfigs = shardConfigDao
        .getAllShards(ShardType.USER.getType());
    @SuppressWarnings("unchecked")
    List<Object> followedTagsList = new ArrayList<Object>();

    for (ShardConfig config : shardConfigs) {
      followedTagsList.addAll(followTagDao.getfollowedTagsList(config.getId(), userId));
    }
    return (followedTagsList.size() > 0) ? followedTagsList : null;
  }

}
