package com.inncretech.comment.service;

import java.util.List;

import com.inncretech.comment.model.Comment;


public interface CommentService {

  public Comment create(Long sourceId, Comment comment);
  public List<Comment> getAllComments(Long sourceId);
  
}
