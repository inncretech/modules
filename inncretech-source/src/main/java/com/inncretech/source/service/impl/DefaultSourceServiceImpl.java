package com.inncretech.source.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.source.dao.SourceDao;
import com.inncretech.source.model.Source;
import com.inncretech.source.service.SourceService;

@Service
public class DefaultSourceServiceImpl implements SourceService {

  @Autowired
  private SourceDao sourceDao;

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private ShardConfigDao shardConfigDao;

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source create(Source source) {
    sourceDao.save(source);
    return source;
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public Source get(Long sourceId) {
    return sourceDao.get(sourceId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void delete(Long sourceId) {
    sourceDao.delete(sourceId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<Long> getSourceByMagazineId(Long magazineId) {
    List<Source> sources = sourceDao.getSourceByMagazineId(magazineId);
    List<Long> ids = new ArrayList<Long>();
    for (Source source : sources) {
      ids.add(source.getId());
    }

    return ids;
  }

  public Map<Long, Long> getSourceByMagazineMap(List<Long> sourceIds) {

    Map<Integer, List<Long>> buckets = sourceDao.bucketizeEntites(sourceIds);
    Map<Long, Long> result = new HashMap<Long, Long>();

    for (Map.Entry<Integer, List<Long>> entry : buckets.entrySet()) {
      List<Source> sources = sourceDao.getSources(entry.getKey(), entry.getValue());
      for (Source source : sources) {
        result.put(source.getId(), source.getMagazineId());
      }
    }

    return result;
  }

  public List<Source> getSources(List<Long> sourceIds) {
    Map<Integer, List<Long>> buckets = sourceDao.bucketizeEntites(sourceIds);
    List<Source> result = new ArrayList<Source>();
    for (Map.Entry<Integer, List<Long>> entry : buckets.entrySet()) {
      List<Source> sources = sourceDao.getSources(entry.getKey(), entry.getValue());
      result.addAll(sources);
    }

    return result;
  }
}
