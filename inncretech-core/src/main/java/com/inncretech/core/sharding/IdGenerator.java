package com.inncretech.core.sharding;

import java.util.List;
import java.util.Map;

public interface IdGenerator {
  
  public Long getNewUserId();
  public Long getNewSourceId();
  Integer getShardId(Long id, ShardType shardType);
  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds, ShardType shardType);

}
