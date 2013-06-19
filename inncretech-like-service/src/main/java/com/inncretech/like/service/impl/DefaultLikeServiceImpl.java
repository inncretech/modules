package com.inncretech.like.service.impl;

import java.util.List;

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

  @Override
  public List<SourceLike> getAllLikesByObject(Long objectId) {
    List<SourceLike> allSourceLikes = srcLikeDao.getAllLikes(objectId);
    
    return allSourceLikes;
  }

  @Override
  public List<SourceLike> getAllLikeByUser(Long userId) {
    List<SourceLike> allSourceLikes = srcLikeDao.getAllLikesByUser(userId);
    
    return allSourceLikes;
  }
  @Override
  public void likeSource(Long srcID , LikeType likeType) {
    SourceLike likeSrc = new SourceLike();
    likeSrc.setId(srcLikeDao.getIdGenService().getIdOnShard(srcLikeDao.getIdGenService().getShardId(srcID, ShardType.SOURCE)));
    likeSrc.setObjectId(srcID);
    likeSrc.setUserId(AccessContext.get().getCallerUserId());
    likeSrc.setLikeValue((byte) 1);
    srcLikeDao.likeObject(likeSrc);
    
  }

  @Autowired
  private SourceLikeDao srcLikeDao;

}
