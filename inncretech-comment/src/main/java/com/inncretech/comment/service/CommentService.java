package com.inncretech.comment.service;

public interface CommentService {

  Object create(Long sourceId, Object commentDto);
  Object getAllComments(Long sourceId);
  
}
