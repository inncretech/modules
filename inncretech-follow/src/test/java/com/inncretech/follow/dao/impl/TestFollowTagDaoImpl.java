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
import com.inncretech.follow.dao.FollowTagDao;
import com.inncretech.follow.model.FollowTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-follow.xml" })
public class TestFollowTagDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private FollowTagDao followTagDao;

  @Test
  public void checkFollowTag() {

    Long followerUserId1 = idGenerator.getNewUserId();
    Long followerUserId2 = idGenerator.getNewUserId();
    Long followerUserId3 = idGenerator.getNewUserId();
    Long tagId1 = System.nanoTime();
    Long tagId2 = System.nanoTime();
    Long tagId3 = System.nanoTime();

    followTagDao.save(createFollowTag(followerUserId1, tagId1));
    followTagDao.save(createFollowTag(followerUserId1, tagId2));
    followTagDao.save(createFollowTag(followerUserId1, tagId3));

    followTagDao.save(createFollowTag(followerUserId2, tagId1));
    followTagDao.save(createFollowTag(followerUserId3, tagId1));

    List<Long> tagIds = followTagDao.getFollowedTagsByUser(followerUserId1);

    Assert.state(tagIds != null);
    Assert.state(tagIds.size() == 3);

    List<Long> followerUserIds = followTagDao.getFollowersByTag(tagId1);
    Assert.state(followerUserIds != null);
    Assert.state(followerUserIds.size() == 3);

    FollowTag result = followTagDao.getFollowTag(followerUserId1, tagId1);
    Assert.state(result != null);
  }

  private FollowTag createFollowTag(Long followerUserId, Long tagId) {
    FollowTag followTag = new FollowTag();
    followTag.setTagId(tagId);
    followTag.setFollowerId(followerUserId);
    followTag.setId(idGenerator.getNewIdOnSourceShard(followerUserId));
    followTag.setRecordStatus(RecordStatus.ACTIVE.getId());
    followTag.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followTag.setCreatedBy(followerUserId);
    followTag.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    followTag.setUpdatedBy(followerUserId);
    return followTag;
  }

}
