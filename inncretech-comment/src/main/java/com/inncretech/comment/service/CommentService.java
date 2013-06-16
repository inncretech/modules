package com.inncretech.comment.service;

import java.util.List;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.model.AccessContext;

public interface CommentService {

<<<<<<< HEAD
  public Comment create(Long sourceId, Comment comment,AccessContext accessContext);
  public List<Comment> getAllComments(Long sourceId);
=======
  Object create(Long sourceId, String comment);
  Object getAllComments(Long sourceId);
>>>>>>> 3bb2c5afddc8fc2cb649ca7bc18a120a97589047
  
}
