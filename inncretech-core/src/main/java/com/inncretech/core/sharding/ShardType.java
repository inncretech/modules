package com.inncretech.core.sharding;

public enum ShardType {
  USER(0), SOURCE(1), NOT_KNOWN(9999);

  private int type;

  private ShardType(int type) {
    this.type = type;
  }

  public int getBaseId() {
    return (8000 * type) + 1;
  }
}
