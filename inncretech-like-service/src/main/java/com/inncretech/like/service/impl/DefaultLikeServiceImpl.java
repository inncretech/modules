package com.inncretech.like.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.model.ShardConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;

@Service
public class DefaultLikeServiceImpl implements LikeService{

    @Autowired
    private ShardConfigDao shardConfigDao;

  @Override
  public List<SourceLike> getAllLikesBySource(Long sourceIdId) {
    List<SourceLike> allSourceLikes = srcLikeDao.getAllLikes(sourceIdId);
    
    return allSourceLikes;
  }

  @Override
  public List<SourceLike> getAllLikeByUser(Long userId) {
      List<SourceLike> allSourceLikes = new ArrayList<SourceLike>();
      List<ShardConfig> allShards = shardConfigDao.getAllShards(ShardType.SOURCE.getType());
      for(ShardConfig config : allShards){
          allSourceLikes.addAll(srcLikeDao.getAllLikesByUser(config.getId(), userId)) ;
      }

    return allSourceLikes;
  }
  @Override
  public void likeSource(Long srcID , LikeType likeType, Long createdBy) {
    SourceLike likeSrc = new SourceLike();
    likeSrc.setId(srcLikeDao.getIdGenService().getIdOnShard(srcLikeDao.getIdGenService().getShardId(srcID, ShardType.SOURCE)));
    likeSrc.setObjectId(srcID);
    likeSrc.setUserId(createdBy);
    likeSrc.setLikeValue((byte) 1);
    srcLikeDao.likeObject(likeSrc);
    
  }

  @Autowired
  private SourceLikeDao srcLikeDao;

}
