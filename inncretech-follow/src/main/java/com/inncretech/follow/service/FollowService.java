package com.inncretech.follow.service;

import java.util.List;


public interface FollowService {
  
  void followTag(Long tagId);
  void followSource(Long tagId);
  void followUser(Long tagId);
  
  List<Object> getFollowersBySource(Long sourceId);
  List<Object> getFollowersByUser(Long userId);
  List<Object> getFollowersByTag(Long tagId);
  
  List<Object> getFollowedSources(Long userId);
  List<Object> getFollowedUsers(Long userId);
  List<Object> getFollowedTags(Long userId);

}
