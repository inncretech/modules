package com.inncretech.like.dao.impl;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-like.xml" })
public class TestSourceLikeDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private SourceLikeDao sourceLikeDao;

  @Test
  public void saveSourceLike() {
    Long userId = idGenerator.getNewUserId();
    Long sourceId = idGenerator.getNewSourceId();
    SourceLike sourceLike = createSourceLike();
    sourceLike.setCreatedBy(userId);
    sourceLike.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    sourceLike.setLikeValue(LikeType.LIKE.getValue());
    sourceLike.setObjectId(sourceId);
    sourceLike.setUserId(userId);
    Long sourceLikeId = sourceLikeDao.save(sourceLike);

    Assert.state(sourceLikeId > 0);

    List<SourceLike> sourceLikes = sourceLikeDao.getAllLikes(Collections.singletonList(sourceId));

    Assert.state(sourceLikes != null);
    Assert.state(sourceLikes.size() == 1);
    Assert.state(sourceLikes.get(0).getObjectId().equals(sourceId));

    int shardId = idGenerator.getShardId(sourceLikeId, ShardType.SOURCE);
    sourceLikes = sourceLikeDao.getAllLikesByUser(shardId, userId);

    Assert.state(sourceLikes != null);
    Assert.state(sourceLikes.size() == 1);
    Assert.state(sourceLikes.get(0).getObjectId().equals(sourceId));

    sourceLikes = sourceLikeDao.getAllLikesByUser(shardId, userId, Boolean.TRUE);
    Assert.state(sourceLikes != null);
    Assert.state(sourceLikes.size() == 1);
    Assert.state(sourceLikes.get(0).getObjectId().equals(sourceId));

    sourceLikes = sourceLikeDao.getAllLikesByUser(shardId, userId, Boolean.FALSE);
    Assert.state(sourceLikes == null || sourceLikes.size() == 0);
  }

  private SourceLike createSourceLike() {
    SourceLike sourceLike = new SourceLike();
    sourceLike.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    return sourceLike;
  }
}
