package com.inncretech.comment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-comment.xml" })
@Service
public class DefaultCommentServiceImplTest {

  List<Comment> commList = new ArrayList<Comment>();

  @Autowired
  public CommentService commentService;

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private TestUtil dbUtility;

  @Autowired
  private TestCommentUtil testCommentUtil;

  Long firstCommentId;
  Long lastCommentId1;
  Long lastCommentId2;

  @Before
  public void setUp() {
    dbUtility.cleanUpdb();

  }

  @Test
  public void testCreateAndGetComments() {
    Comment comment = new Comment();
    Long userId = idGenerator.getNewUserId();
    Long sourceId = idGenerator.getNewSourceId();

    commentService.create(sourceId, "Comment1", userId, null);
    firstCommentId = testCommentUtil.getFirstCommentId(sourceId);

    comment.setCommentParentId(firstCommentId);
    commentService.create(sourceId, "Comment1", userId, firstCommentId);
    commentService.create(sourceId, "Comment1", userId, firstCommentId);

    lastCommentId1 = testCommentUtil.getLastEnteredCommentId(sourceId);
    comment.setCommentParentId(lastCommentId1);
    commentService.create(sourceId, "Comment1", userId, lastCommentId1);
    commentService.create(sourceId, "Comment1", userId, lastCommentId1);
    commentService.create(sourceId, "Comment1", userId, lastCommentId1);

    lastCommentId2 = testCommentUtil.getLastEnteredCommentId(sourceId);
    comment.setCommentParentId(lastCommentId2);
    commentService.create(sourceId, "Comment1", userId, lastCommentId2);
    commentService.create(sourceId, "Comment1", userId, lastCommentId2);
    commentService.create(sourceId, "Comment1", userId, lastCommentId2);
    commentService.create(sourceId, "Comment1", userId, lastCommentId2);

    commList = commentService.getAllComments(sourceId);

    // commList contains top level comments, Top level comments are comments
    // with commentParentId = null
    // assertEquals(1,commList.size());

    System.out.println("Comment List size : " + commList.size());

    for (Comment cmnt : commList) {
      System.out.println(" " + cmnt.getId());
      getChildComments(cmnt, "  ");
    }
  }

  public void getChildComments(Comment comm, String inc) {
    for (Comment comm1 : comm.getChildComments()) {
      System.out.println(inc + comm1.getId());
      if (comm1.getId().equals(firstCommentId)) {
        Assert.assertEquals(2, comm1.getChildComments().size());
      }
      if (comm1.getId().equals(lastCommentId1)) {
        assertEquals(3, comm1.getChildComments().size());
      }
      if (comm1.getId().equals(lastCommentId2)) {
        assertEquals(4, comm1.getChildComments().size());
      }
      getChildComments(comm1, inc + " ");
    }
  }
}