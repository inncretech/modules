/*package com.inncretech.comment;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;

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
	  comment.setCommentParentId(18L);
	  comment.setComment("Comment1");
	  commentService.create(comment.getSourceId(), comment);
  }
  
  @Test
  public void getAllComments() {
	//Pass the SourceId which exists in database
	commentService.getAllComments(2281412141594445849L);
  }
  
  public static void main(String[] args) {
    
  }

}
*/

package com.inncretech.comment;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;
import com.inncretech.core.sharding.IdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationcontext-comment.xml" })
public class DefaultCommentServiceImplTest {

	List<Comment> commList = new ArrayList<Comment>();
  @Autowired
  private CommentService commentService;
  
  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void createComment() {
	  Comment comment = new Comment();
	  comment.setUserId(idGenerator.getNewUserId());
	  comment.setSourceId(idGenerator.getNewSourceId());
	  comment.setCommentParentId(3L);
	  comment.setComment("Comment1");
	  commentService.create(comment.getSourceId(), comment);
  }
  
  @Test
  public void getAllComments() {
	//Pass the SourceId which exists in comment table
	 commList = commentService.getAllComments(2281412141594445849L);
	for (Comment comment : commList) {
		System.out.println("Comment Id : "+comment.getId());
		System.out.println("No of Childs : "+comment.getChildComments().size());
		getChildComments(comment);
	  }
  }
  
  public  void getChildComments(Comment comm) {	 
	    for (Comment comm1 : comm.getChildComments()) {
	        System.out.println("Comment Id is : "+comm1.getId()+ " Parent Id is : "+comm1.getCommentParentId());
	        System.out.println("No of Childs : "+comm1.getChildComments().size());
	        getChildComments(comm1);
	    }	    
	}

  public static void main(String[] args) {
    
  }

}