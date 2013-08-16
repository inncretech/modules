package com.inncretech.comment.service;

import java.util.List;

import com.inncretech.comment.model.Comment;

public interface CommentService {
	public Comment create(Long sourceId, String commentText, Long createdBy, Long parentCommentId);
	public List<Comment> getAllComments(Long sourceId);

}
