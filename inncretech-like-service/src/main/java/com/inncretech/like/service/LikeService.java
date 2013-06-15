package com.inncretech.like.service;

import java.util.List;

import com.inncretech.core.model.AccessContext;
import com.inncretech.like.model.SourceLike;

public interface LikeService {
  
  List<SourceLike> getAllLikesByObject(Long objectId, AccessContext accessContext);
  
  List<SourceLike> getAllLikeByUser(Long userId, AccessContext accessContext);
  
  void likeSource(Long objectID,Long  userId, AccessContext accessContext);
}
