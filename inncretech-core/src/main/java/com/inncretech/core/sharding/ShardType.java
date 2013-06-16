package com.inncretech.core.sharding;

public enum ShardType {
  USER(0), SOURCE(1), NOT_KNOWN(9999);

  private int type;

  private ShardType(int type) {
    this.type = type;
  }
  
  public int getType(){
    return type;
  }

  public int getBaseId() {
    return 8000 * type;
  }
  
  public static int getLogicalShardId(int dbShardId){
    return dbShardId % 8000;
  }
  
  public int getDbShardId(int encodedshardId){
    return getBaseId()+encodedshardId;
  }
}
