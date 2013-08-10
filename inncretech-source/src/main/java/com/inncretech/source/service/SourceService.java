package com.inncretech.source.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.model.Source;

public interface SourceService {

  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  Source create(Source source);
  
  void delete(Long sourceId);
  
  Source get(Long sourceId);

}
