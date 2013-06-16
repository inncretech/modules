package com.inncretech.like.service;

import java.util.List;

import com.inncretech.core.model.AccessContext;
import com.inncretech.like.model.Like;

public interface LikeService {
  
  List<Like> getAllLikesByObject(Long objectId, AccessContext accessContext);
  
  List<Like> getAllLikeByUser(Long userId, AccessContext accessContext);
  
  void likeSource(Long srcID ,Long userId, AccessContext accessContext);
}
