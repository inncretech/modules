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
import com.inncretech.follow.model.FollowSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
public class TestFollowSourceDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private FollowSourceDao followSourceDao;

  @Test
  public void followSource() {

    Long followerUserId1 = idGenerator.getNewUserId();
    Long followerUserId2 = idGenerator.getNewUserId();
    Long followerUserId3 = idGenerator.getNewUserId();
    Long followedSourceId1 = idGenerator.getNewSourceId();
    Long followedSourceId2 = idGenerator.getNewSourceId();
    Long followedSourceId3 = idGenerator.getNewSourceId();

    followSourceDao.save(createFollowSource(followerUserId1, followedSourceId1));
    followSourceDao.save(createFollowSource(followerUserId1, followedSourceId2));
    followSourceDao.save(createFollowSource(followerUserId1, followedSourceId3));

    followSourceDao.save(createFollowSource(followerUserId2, followedSourceId1));
    followSourceDao.save(createFollowSource(followerUserId3, followedSourceId1));

    List<FollowSource> followSources = followSourceDao.getFollowedSources(followerUserId1);

    Assert.state(followSources != null);
    Assert.state(followSources.size() == 3);

    followSources = followSourceDao.getFollowersBySource(followedSourceId1);
    Assert.state(followSources != null);
    Assert.state(followSources.size() == 3);

    FollowSource result = followSourceDao.getFollowSource(followedSourceId1, followerUserId1);
    Assert.state(result != null);
  }

  private FollowSource createFollowSource(Long followerUserId, Long sourceId) {
    FollowSource followSource = new FollowSource();
    followSource.setSourceId(sourceId);
    followSource.setFollowerId(followerUserId);
    followSource.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    followSource.setRecordStatus(RecordStatus.ACTIVE.getId());
    followSource.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followSource.setCreatedBy(followerUserId);
    followSource.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followSource.setUpdatedBy(followerUserId);
    return followSource;
  }

}
