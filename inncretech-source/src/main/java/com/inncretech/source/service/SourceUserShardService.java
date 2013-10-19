package com.inncretech.source.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.model.Source;

public interface SourceUserShardService {

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  Source create(Source source);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  void delete(Long sourceId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  Source get(Long sourceId);

}
