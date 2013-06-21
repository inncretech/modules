package com.inncretech.comment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.comment.dao.CommentDao;
import com.inncretech.comment.model.Comment;
import com.inncretech.comment.service.CommentService;

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
		Map<Long, Comment> commentMap = new HashMap<Long, Comment>();
		for(Comment comment : listComment){
		  if(comment.getCommentParentId() !=null && !commentMap.containsKey(comment.getCommentParentId())){
		    throw new RuntimeException("THis conidtion should not happen");
		  }
		  if(comment.getCommentParentId() !=null ){
        Comment parentComment = commentMap.get(comment.getCommentParentId());
        parentComment.getChildComments().add(comment);
        commentMap.put(comment.getId(), comment);
      }else{
        commentMap.put(comment.getId(), comment);
      }
		  
		}
		List<Comment> firstLevelComments = new ArrayList<Comment>();
		for(Long commentId : commentMap.keySet())
		{
		  if(commentMap.get(commentId).getCommentParentId() == null)
		    firstLevelComments.add(commentMap.get(commentId));
		}
		return firstLevelComments;
	}
}
