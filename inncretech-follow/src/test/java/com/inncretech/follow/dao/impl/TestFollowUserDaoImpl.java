package com.inncretech.follow.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.follow.dao.FollowSourceDao;
import com.inncretech.follow.dao.FollowUserDao;
import com.inncretech.follow.model.FollowUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
public class TestFollowUserDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private FollowUserDao followUserDao;

  @Test
  public void followUser() {

    Long followerUserId1 = idGenerator.getNewUserId();
    Long followerUserId2 = idGenerator.getNewUserId();
    Long followerUserId3 = idGenerator.getNewUserId();
    Long followedUserId1 = idGenerator.getNewUserId();
    Long followedUserId2 = idGenerator.getNewUserId();
    Long followedUserId3 = idGenerator.getNewUserId();

    followUserDao.save(createFollowUser(followerUserId1, followedUserId1));
    followUserDao.save(createFollowUser(followerUserId1, followedUserId2));
    followUserDao.save(createFollowUser(followerUserId1, followedUserId3));

    followUserDao.save(createFollowUser(followerUserId2, followedUserId1));
    followUserDao.save(createFollowUser(followerUserId3, followedUserId1));

    List<FollowUser> followUsers = followUserDao.getFollowedUsersByUser(followerUserId1);

    Assert.state(followUsers != null);
    Assert.state(followUsers.size() == 3);

    followUsers = followUserDao.getFollowerUsersForUser(followedUserId1);
    Assert.state(followUsers != null);
    Assert.state(followUsers.size() == 3);
    
    FollowUser result = followUserDao.getFollowUser(followerUserId1, followedUserId1);
    Assert.state(result != null);
  }

  private FollowUser createFollowUser(Long followerUserId, Long followedUserId) {
    FollowUser followUser = new FollowUser();
    followUser.setFollowerUserId(followerUserId);
    followUser.setFollowedUserId(followedUserId);
    followUser.setId(idGenerator.getNewIdOnUserShard(followerUserId));
    followUser.setRecordStatus(RecordStatus.ACTIVE.getId());
    followUser.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followUser.setCreatedBy(followerUserId);
    followUser.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followUser.setUpdatedBy(followerUserId);
    return followUser;
  }

}
