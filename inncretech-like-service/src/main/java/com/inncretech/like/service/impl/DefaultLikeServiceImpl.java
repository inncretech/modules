package com.inncretech.like.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.core.util.DateTimeUtils;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;

@Service
public class DefaultLikeServiceImpl implements LikeService {

  @Autowired
  private SourceLikeDao srcLikeDao;

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public List<SourceLike> getAllLikesBySource(Long sourceIdId) {
    List<SourceLike> allSourceLikes = srcLikeDao.getAllLikes(sourceIdId);

    return allSourceLikes;
  }

  @Override
  public List<SourceLike> getAllLikeByUser(Long userId) {
    List<SourceLike> allSourceLikes = new ArrayList<SourceLike>();
    List<ShardConfig> allShards = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
    for (ShardConfig config : allShards) {
      allSourceLikes.addAll(srcLikeDao.getAllLikesByUser(config.getId(), userId));
    }

    return allSourceLikes;
  }

  @Override
  public SourceLike likeSource(Long srcID, LikeType likeType, Long createdBy) {
    SourceLike likeSrc = new SourceLike();
    likeSrc.setId(idGenerator.getNewIdOnSourceShard(srcID));
    likeSrc.setObjectId(srcID);
    likeSrc.setUserId(createdBy);
    likeSrc.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    likeSrc.setLikeValue(likeType.getValue());
    srcLikeDao.likeObject(likeSrc);
    return likeSrc;
  }

}
