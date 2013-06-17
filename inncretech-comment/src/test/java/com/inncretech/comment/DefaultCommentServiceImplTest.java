package com.inncretech.comment;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.IdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationcontext-comment.xml" })
public class DefaultCommentServiceImplTest {

  @Autowired
  private CommentService commentService;
  
  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void createComment() {
	  Comment comment = new Comment();
	  comment.setUserId(idGenerator.getNewUserId());
	  comment.setSourceId(idGenerator.getNewSourceId());
	  comment.setCommentParentId(10101L);
	  comment.setComment("Comment1");
	  commentService.create(idGenerator.getNewSourceId(), comment);
  }
  
  @Test
  public void getAllComments() {	  
	commentService.getAllComments(2276911242738142225L);
  }
  
  public static void main(String[] args) {
    
  }

}
