package com.inncretech.source.service;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.source.model.Source;

import java.util.List;
import java.util.Map;


public interface SourceService {

  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  Source create(Source source);
  
  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  void delete(Long sourceId);
  
  @ShardAware(shardStrategy="entityid", shardType= ShardType.SOURCE)
  Source get(Long sourceId);

  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  List<Long> getSourceByMagazineId(Long magazineId);

  Map<Long, Long> getSourceByMagazineMap(List<Long> sourceIds);

  List<Source> getSources(List<Long> sourceIds);

}
