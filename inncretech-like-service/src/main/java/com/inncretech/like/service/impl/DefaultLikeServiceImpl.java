package com.inncretech.like.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.model.AccessContext;
import com.inncretech.like.dao.ObjectLikeDao;
import com.inncretech.like.model.Like;
import com.inncretech.like.service.LikeService;

@Service
public class DefaultLikeServiceImpl implements LikeService{

  @Override
  public List<Like> getAllLikesByObject(Long objectId, AccessContext accessContext) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Like> getAllLikeByUser(Long userId, AccessContext accessContext) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public void likeSource(Long userId,Long objectID , AccessContext accessContext) {
    Like likeObj = new Like();
    likeObj.setId(objLikeDao.getIdGenService().getNewSourceId());
    likeObj.setObjectId(objectID);
    likeObj.setUserId(userId);
    objLikeDao.likeObject(likeObj);
    
  }
    

  @Autowired
  private ObjectLikeDao objLikeDao;

}
