package com.inncretech.comment.service.impl;

import java.util.ArrayList;
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
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment create(Long sourceId, Comment comment) {
		comment.setCommentDate(new Date());
		commentDao.createComment(sourceId, comment);
		return comment;
	}

	@Override
	public List<Comment> getAllComments(Long sourceId) {
		  List<Comment> listComment = commentDao.getComments(sourceId); 
		 
		  CommentTree<Comment> rootNode = new CommentTree<Comment>(sourceId);
		  int noOfComments = listComment.size();
		  System.out.println("Comment Size : "+noOfComments);
			for (int i = 0; i < noOfComments; i++) {
				rootNode.addNode(listComment.get(i).getCommentParentId(),
						listComment.get(i).getId());
			}
			String s = rootNode.getCommentTree(1);
			System.out.println(s);
		  
		  
		  return listComment;
		 

	}
}
