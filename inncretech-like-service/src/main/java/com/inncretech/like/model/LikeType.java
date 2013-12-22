package com.inncretech.like.model;

public enum LikeType {

  LIKE(1), UNLIKE(-1);

  private Integer likeValue;

  private LikeType(Integer likeValue) {
    this.likeValue = likeValue;
  }

  public Integer getValue() {
    return likeValue;
  }

}
