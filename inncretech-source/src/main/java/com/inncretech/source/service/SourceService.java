package com.inncretech.source.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.model.Source;


public interface SourceService {

  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  Source create(Source source);
  
  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  void delete(Long sourceId);
  
  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  Source get(Long sourceId);

}
