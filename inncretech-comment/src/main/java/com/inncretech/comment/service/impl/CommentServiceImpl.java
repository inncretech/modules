package com.inncretech.comment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

		List<Comment> commentObjWithChildList = new ArrayList<Comment>();
		for (Comment tempComment1 : listComment) {
			Comment comment = new Comment();
			List<Comment> childNode = new ArrayList<Comment>();
			for (Comment tempComment2 : listComment) {
				if (tempComment1.getId() == tempComment2.getCommentParentId()) {
					childNode.add(tempComment2);
				}
			}
			comment = tempComment1;
			comment.setChildComments(childNode);
			commentObjWithChildList.add(comment);
		}
		return commentObjWithChildList;
	}
}
