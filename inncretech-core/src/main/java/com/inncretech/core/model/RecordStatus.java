package com.inncretech.core.model;

public enum RecordStatus {
  ACTIVE((byte)1) , INACTIVE((byte)0);
  
  private byte id;
  private RecordStatus(byte id){
    this.id = id;
  }
  
  public byte getId(){
    return this.id;
  }
}
