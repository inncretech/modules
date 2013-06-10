package com.inncretech.source.dao;

import org.springframework.stereotype.Component;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.source.model.Source;

@Component
public class SourceDao extends AbstractShardAwareHibernateDao<Source>{
  
  public SourceDao(){
    super(Source.class, ShardType.SOURCE);
  }
  
  @ShardAware(shardStrategy="entityid", shardType=ShardType.SOURCE)
  public Source createSource(Source source){
    save(source.getId(), source);
    return source;
  }
  
}
