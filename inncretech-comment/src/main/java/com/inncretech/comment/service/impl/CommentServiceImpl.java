package com.inncretech.comment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.comment.model.CommentTree;
import com.inncretech.comment.service.CommentService;
import com.inncretech.core.model.AccessContext;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment create(Long sourceId, Comment comment,AccessContext accessContext) {
		comment.setCommentDate(new Date());
		commentDao.createComment(sourceId, comment);
		return comment;
	}

	@Override
	public List<Comment> getAllComments(Long sourceId) {
		//TODO change return type of method after processing the comment object in tree structure

		List<Comment> listComment= commentDao.getComments(sourceId);
		return listComment;
		
		//TODO Remove the hardcoded values and get the actual values from database and create comment tree
		/*List<Comment> commentList = new ArrayList<Comment>();
		Comment c1= new Comment();
		c1.setComment("Comment C1");
		c1.setCommentParentId(2276911242738142225L);
		c1.setId(1L);
		c1.setSourceId(2276911242738142225L);
		c1.setUserId(2277105480813774859L);
		commentList.add(c1);
		Comment c2= new Comment();
		c2.setComment("Comment C2");
		c2.setCommentParentId(1L);
		c2.setId(2L);
		c2.setSourceId(2277105481862352921L);
		c2.setUserId(2277105480813774859L);
		commentList.add(c2);
		Comment c3= new Comment();
		c3.setComment("Comment C3");
		c3.setCommentParentId(2L);
		c3.setId(3L);
		c3.setSourceId(2277105481862352921L);
		c3.setUserId(2277105480813774859L);
		commentList.add(c3);
		Comment c4= new Comment();
		c4.setComment("Comment C4");
		c4.setCommentParentId(1L);
		c4.setId(4L);
		c4.setSourceId(2277105481862352921L);
		c4.setUserId(2277105480813774859L);
		commentList.add(c4);
		CommentTree<Comment> rootNode = new CommentTree<Comment>(sourceId);		
		int noOfComments = commentList.size();
		for(int i=0;i<noOfComments;i++){
			 rootNode.addNode(commentList.get(i).getCommentParentId(),commentList.get(i).getId());
		}
		String s=rootNode.getCommentTree(1); 
	    System.out.println(s);
		return null;   	  */
	}
}
