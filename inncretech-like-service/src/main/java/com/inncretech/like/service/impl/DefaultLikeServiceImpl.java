package com.inncretech.like.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;

@Service
public class DefaultLikeServiceImpl implements LikeService{

  @Override
  public List<SourceLike> getAllLikesByObject(Long objectId, AccessContext accessContext) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<SourceLike> getAllLikeByUser(Long userId, AccessContext accessContext) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public void likeSource(Long srcID, Long userId,AccessContext accessContext) {
    SourceLike likeObj = new SourceLike();
    likeObj.setId(srcLikeDao.getIdGenService().getIdOnShard(srcLikeDao.getIdGenService().getShardId(srcID, ShardType.SOURCE)));
    likeObj.setObjectId(srcID);
    likeObj.setUserId(userId);
    srcLikeDao.likeObject(likeObj);
    
  }
    

  @Autowired
  private SourceLikeDao srcLikeDao;

}
