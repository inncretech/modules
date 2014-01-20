package com.inncretech.core.model;

public enum RecordStatus {
  ACTIVE((byte)1) , INACTIVE((byte)0), DELETED(null);
  
  private Byte id;
  private RecordStatus(Byte id){
    this.id = id;
  }
  
  public Byte getId(){
    return this.id;
  }
}
