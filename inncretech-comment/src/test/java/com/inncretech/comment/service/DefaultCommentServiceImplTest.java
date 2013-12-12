package com.inncretech.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.comment.model.Comment;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-comment.xml" })
public class DefaultCommentServiceImplTest {

  List<Comment> commList = new ArrayList<Comment>();

  @Autowired
  public CommentService commentService;

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private TestUtil dbUtility;

  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] { "comment" });

  }

  @Test
  public void checkService() {
    Long userId = idGenerator.getNewUserId();
    Long sourceId = idGenerator.getNewSourceId();

    Comment parentComment = commentService.create(sourceId, "Comment1", userId, null);
    Assert.state(parentComment.getId() > 0);
    List<Comment> comments = commentService.getAllComments(sourceId);
    Assert.state(comments != null);
    Assert.state(comments.size() == 1);
    Assert.state(comments.get(0).getSourceId().equals(sourceId));

    Comment childComment = commentService.create(sourceId, "Comment1", userId, parentComment.getId());

    Assert.state(childComment.getId() > 0);
    comments = commentService.getAllComments(sourceId);
    Assert.state(comments != null);
    Assert.state(comments.size() == 1);
    Assert.state(comments.get(0).getSourceId().equals(sourceId));
    Assert.state(comments.get(0).getChildComments() != null);
    Assert.state(comments.get(0).getChildComments().size() == 1);
    Assert.state(comments.get(0).getChildComments().get(0).getId().equals(childComment.getId()));
  }
}