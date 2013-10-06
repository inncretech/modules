package com.inncretech.user.model;

public enum UserStatus {
  ACTIVE((byte)1) , INACTIVE((byte)0);
  
  private byte id;
  private UserStatus(byte id){
    this.id = id;
  }
  
  public byte getId(){
    return this.id;
  }
}
