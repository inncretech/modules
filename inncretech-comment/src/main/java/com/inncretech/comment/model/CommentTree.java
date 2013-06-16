package com.inncretech.comment.model;
import java.util.ArrayList;
import java.util.HashMap;

public class CommentTree<Comment> {
  private Long sourceId;
  private ArrayList<CommentTree<Comment>> nodes = new ArrayList<CommentTree<Comment>>();
  private HashMap<Long, CommentTree<Comment>> commentMap = new HashMap<Long, CommentTree<Comment>>();
  private CommentTree<Comment> parent = null;
  private static final int indent = 2;

  public CommentTree(Long sourceId) {
    this.sourceId = sourceId;
    commentMap.put(sourceId, this);
  }
  
 
 public void addNode(Long commentParentId, Long commentId) {
    if (commentMap.containsKey(commentParentId)) {
    	commentMap.get(commentParentId).addNode(commentId);
    }
  }

/*  public void addLeaf(Long commentParentId, Comment comment) {
	    if (commentMap.containsKey(commentParentId)) {
	    	commentMap.get(commentParentId).addLeaf(comment);
	    }
  }
  public CommentTree<Comment> addLeaf(Comment comment) {
	  CommentTree<Comment> commentTree = new CommentTree<Comment>(comment.getId);
    leafs.add(commentTree);
    commentTree.parent = this;
    commentTree.commentMap = this.commentMap;    
    commentMap.put(commentId, commentTree);
    return commentTree;
  }*/
  
  public CommentTree<Comment> addNode(Long commentId) {
	  CommentTree<Comment> commentTree = new CommentTree<Comment>(commentId);
    nodes.add(commentTree);
    commentTree.parent = this;
    commentTree.commentMap = this.commentMap;    
    commentMap.put(commentId, commentTree);
    return commentTree;
  }

  public String getCommentTree(int increment) {
    String comment = "";
    String inc = "";
    for (int i = 0; i < increment; ++i) {
      inc = inc + " ";
    }
    comment = inc + sourceId;
    for (CommentTree<Comment> child : nodes) {
    	comment += "\n" + child.getCommentTree(increment + indent);
    }
    return comment;
  }
 }