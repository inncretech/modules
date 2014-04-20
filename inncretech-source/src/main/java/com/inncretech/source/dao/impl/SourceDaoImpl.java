package com.inncretech.source.dao.impl;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.impl.GenericSourceShardDaoImpl;
import com.inncretech.source.dao.SourceDao;
import com.inncretech.source.model.Source;

import java.util.List;

@Component
public class SourceDaoImpl extends GenericSourceShardDaoImpl<Source, Long> implements SourceDao {

  public SourceDaoImpl() {
    super(Source.class);
  }

  public Source createSource(Source source) {
    save(source);
    return source;
  }

  @SuppressWarnings("unchecked")
  public List<Source> getSourceByMagazineId(Long magazineId) {
    Query q = getQuery(getIdGenService().getShardId(magazineId, ShardType.SOURCE), " from Source where magazineId = :magazineId");
    q.setParameter("magazineId", magazineId);

    return q.list();
  }

  @SuppressWarnings("unchecked")
  public List<Source> getSourceByMagazineId(Integer shardId, List<Long> magazineIds) {
    Query q = getQuery(shardId, " from Source where magazineId in (:magazineIds) ");
    q.setParameterList("magazineIds", magazineIds);

    return q.list();
  }

  @SuppressWarnings("unchecked")
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  public List<Source> getSources(Integer shardId, List<Long> sourceIds) {
    Query q = getQuery(shardId, " from Source where id in (:ids) ");
    q.setParameterList("ids", sourceIds);

    return q.list();
  }
}
