package com.inncretech.comment.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.util.DateTimeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-comment.xml" })
public class TestCommentDaoImpl {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private CommentDao commentDao;

  @Test
  public void saveComment() {
    Long userId = idGenerator.getNewUserId();
    Long sourceId = idGenerator.getNewSourceId();
    Comment parentComment = createComment(null);
    parentComment.setCreatedBy(userId);
    parentComment.setUpdatedBy(userId);
    parentComment.setSourceId(sourceId);
    parentComment.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    Long parentCommentId = commentDao.save(parentComment);

    Assert.state(parentCommentId > 0);
    List<Comment> comments = commentDao.getComments(sourceId);
    Assert.state(comments != null);
    Assert.state(comments.size() == 1);
    Assert.state(comments.get(0).getSourceId().equals(sourceId));

    Comment childComment = createComment(parentCommentId);
    childComment.setCreatedBy(userId);
    childComment.setUpdatedBy(userId);
    childComment.setSourceId(sourceId);
    childComment.setId(idGenerator.getNewIdOnSourceShard(sourceId));
    Long childCommentId = commentDao.save(childComment);

    Assert.state(childCommentId > 0);

    comments = commentDao.getComments(sourceId);
    Assert.state(comments != null);
    Assert.state(comments.size() == 2);
    Assert.state(comments.get(0).getSourceId().equals(sourceId));
    Assert.state(comments.get(1).getSourceId().equals(sourceId));
  }

  private Comment createComment(Long commentParentId) {
    Comment comment = new Comment();
    if (commentParentId != null) {
      comment.setCommentParentId(commentParentId);
    }
    comment.setCommentText("this is comment");
    comment.setCreatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    comment.setRecordStatus(RecordStatus.ACTIVE.getId());
    comment.setUpdatedAt(DateTimeUtils.currentTimeWithoutFractionalSeconds());
    return comment;
  }
}
