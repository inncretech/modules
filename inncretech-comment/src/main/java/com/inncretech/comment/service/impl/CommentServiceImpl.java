package com.inncretech.comment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;
import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.util.DateTimeUtils;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentDao commentDao;

  @Autowired
  private IdGenerator idGenerator;

  @Override
  public Comment create(Long sourceId, String commentText, Long createdBy, Long parentCommentId) {
    Comment comment = new Comment();
    comment.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    comment.setCreatedBy(createdBy);
    comment.setCommentText(commentText);
    comment.setCommentParentId(parentCommentId);
    comment.setSourceId(sourceId);
    comment.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    comment.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    comment.setUpdatedBy(createdBy);
    comment.setRecordStatus(RecordStatus.ACTIVE.getId());
    commentDao.createComment(sourceId, comment);
    return comment;
  }

  @Override
  public List<Comment> getAllComments(Long sourceId) {
    List<Comment> listComment = commentDao.getComments(sourceId);
    Map<Long, Comment> commentMap = new HashMap<Long, Comment>();
    for (Comment comment : listComment) {
      if (comment.getCommentParentId() != null && !commentMap.containsKey(comment.getCommentParentId())) {
        throw new RuntimeException("THis conidtion should not happen");
      }
      if (comment.getCommentParentId() != null) {
        Comment parentComment = commentMap.get(comment.getCommentParentId());
        parentComment.getChildComments().add(comment);
        commentMap.put(comment.getId(), comment);
      } else {
        commentMap.put(comment.getId(), comment);
      }

    }
    List<Comment> firstLevelComments = new ArrayList<Comment>();
    for (Long commentId : commentMap.keySet()) {
      if (commentMap.get(commentId).getCommentParentId() == null)
        firstLevelComments.add(commentMap.get(commentId));
    }
    return firstLevelComments;
  }

  @Override
  public Comment deleteComment(Comment comment) {
    return commentDao.deleteComment(comment.getId());
  }

  @Override
  public Comment editComment(Comment comment) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment flagCommentAsAbuse(Comment comment) {
    // TODO Auto-generated method stub
    return null;
  }
}
