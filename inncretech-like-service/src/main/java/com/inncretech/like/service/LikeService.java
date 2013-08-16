package com.inncretech.like.service;

import java.util.List;

import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;

public interface LikeService {
  
  List<SourceLike> getAllLikesBySource(Long objectId);
  
  List<SourceLike> getAllLikeByUser(Long userId);
  
  void likeSource(Long srcID , LikeType likeType, Long createdBy);

}
