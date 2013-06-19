package com.inncretech.like.model;

public enum LikeType {
  
  LIKE((byte)1), UNLIKE((byte)-1);
  
  private byte likeValue;
  
  private LikeType(byte likeValue)
  {
    this.likeValue = likeValue;
  }
  
  public byte getValue()
  {
    return likeValue;
  }

}
