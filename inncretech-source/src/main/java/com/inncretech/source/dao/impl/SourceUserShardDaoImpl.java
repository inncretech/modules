package com.inncretech.source.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.dao.GenericUserShardDaoImpl;
import com.inncretech.source.dao.SourceUserShardDao;
import com.inncretech.source.model.Source;

@Component
public class SourceUserShardDaoImpl extends GenericUserShardDaoImpl<Source, Long> implements SourceUserShardDao {

  public SourceUserShardDaoImpl() {
    super(Source.class);
  }

  public Source createSource(Source source) {
    save(source);
    return source;
  }

  @Override
  public List<Source> findByCriteria(Integer shardId, DetachedCriteria detachedCriteria) {
    // TODO Auto-generated method stub
    return null;
  }
}
