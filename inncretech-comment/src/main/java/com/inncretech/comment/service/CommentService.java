package com.inncretech.comment.service;

public interface CommentService {

  Object create(Long sourceId, String comment);
  Object getAllComments(Long sourceId);
  
}
