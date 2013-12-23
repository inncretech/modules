package com.inncretech.follow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowSource;
import com.inncretech.follow.model.FollowTag;
import com.inncretech.follow.model.FollowUser;
import com.inncretech.follow.service.FollowService;

@SuppressWarnings("deprecation")
@Service
@Deprecated
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
  public FollowTag followTag(Long userId, Long tagId) {

    if (doesUserFollowTag(userId, tagId)) {
      return null;
    }
    FollowTag followTag = new FollowTag();
    followTag.setId(idGenerator.getNewIdOnUserShard(userId));
    followTag.setTagId(tagId);
    followTag.setFollowerId(userId);
    followTag.setRecordStatus(RecordStatus.ACTIVE.getId());
    followTag.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followTagDao.save(followTag);
    return followTag;

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public boolean doesUserFollowTag(long userId, long tagId) {
    return followTagDao.doesUserFollowTag(userId, tagId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public boolean doesUserFollowAUser(long userId, long followerId) {

    return followUserDao.doesUserFollowAUser(userId, followerId);
  }

  @Override
  public List<FollowTag> getFollowersByTag(Long tagId) {

    return followTagDao.getFollowersByTag(tagId);

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<FollowTag> getFollowedTags(Long userId) {

    return followTagDao.getFollowedTagsByUser(userId);

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowUser followUser(Long followerUserId, Long followedUserId) {

    if (doesUserFollowAUser(followerUserId, followedUserId)) {
      return null;
    }

    FollowUser followUser = new FollowUser();
    followUser.setFollowerUserId(followerUserId);
    followUser.setFollowedUserId(followedUserId);
    followUser.setId(idGenerator.getNewIdOnUserShard(followerUserId));
    followUser.setRecordStatus(RecordStatus.ACTIVE.getId());
    followUser.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followUser.setCreatedBy(followerUserId);
    followUser.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followUser.setUpdatedBy(followerUserId);
    followUserDao.save(followUser);
    return followUser;

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<FollowUser> getFollowersByUser(Long userId) {

    List<FollowUser> followersList = new ArrayList<FollowUser>();
    followersList.addAll(followUserDao.getFollowedUsersByUser(userId));
    return followersList;

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowTag unFollowTag(Long userId, Long tagId) {
    return followTagDao.unfollowTag(userId, tagId);
  }

  @Override
  public void unFollowSource(Long sourceId, Long followerId) {
    // TODO Auto-generated method stub

  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public FollowUser unFollowUser(Long userId, Long followerId) {
    //return followUserDao.unfollowUser(userId, followerId);
    return null;
  }

  @Override
  public List<FollowSource> getFollowersBySource(Long sourceId) {
    List<FollowSource> sourcesList = new ArrayList<FollowSource>();

    System.out.println("Size of Followers List by Source:" + sourcesList.size());

    return (sourcesList.size() > 0) ? sourcesList : null;
  }

  @Override
  public List<FollowSource> getFollowedSources(Long userId) {
    List<FollowSource> sourcesList = new ArrayList<FollowSource>();

    return (sourcesList.size() > 0) ? sourcesList : null;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void followSource(Long sourceId, Long followerId) {
    FollowSource followSource = new FollowSource();
    followSource.setFollowerId(followerId);
    followSource.setSourceId(sourceId);

    //followSourceDao.saveFollowSource(followSource);

  }

  @Override
  public boolean doesUserHasAFollower(long userId, long followerId) {
    // TODO Auto-generated method stub
    return false;
  }

}
