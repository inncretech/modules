package com.inncretech.source.dao;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.source.model.Source;

import java.util.List;

@Component
public interface SourceDao extends GenericSourceShardDAO<Source, Long> {

  List<Source> getSourceByMagazineId(Long magazineId);

  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  List<Source> getSources(Integer shardId, List<Long> sourceIds);
}
