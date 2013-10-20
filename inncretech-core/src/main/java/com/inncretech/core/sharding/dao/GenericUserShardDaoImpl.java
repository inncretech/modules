package com.inncretech.core.sharding.dao;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * A generic Hibernate Data Access Object. This implementation assumes that
 * transactions are being handled by the services layer making use of this DAO.
 * 
 * The DAO makes use of the Hibernate internal thread local session management
 * via spring.
 * 
 * @author shade05
 * 
 */
public abstract class GenericUserShardDaoImpl<T extends BaseEntity, PK extends Serializable> extends AbstractShardAwareHibernateDao<T, PK> implements GenericUserShardDAO<T, PK> {

  @Autowired
  private IdGenerator idGenerator;

  public GenericUserShardDaoImpl(Class<T> type) {
    super(type , ShardType.USER);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void delete(T persistentObject) {
    super.delete(persistentObject);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void delete(PK id) {
    super.delete(id);
  }

  @Override
  public T load(PK id) {
    return super.load(id);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public T get(PK id) {
    return super.get(id);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public PK save(T o) {
    return super.save(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void refresh(T o) {
    super.refresh(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void saveOrUpdate(T o) {
    super.saveOrUpdate(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void update(T o) {
    super.update(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public Query getQuery(Long entityId, String s) {
    return super.getQuery(idGenerator.getShardId(entityId, ShardType.USER), s);
  }

  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
  public List<T> findByCriteria(Integer shardId, Criterion... criterion) {
    return super.findByCriteria(shardId, criterion);
  }

  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
  public List<T> findAll(Integer shardId) {
    return super.findAll(shardId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public void evict(T obj) {
    super.evict(obj);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
  public List<T> findByExample(Integer shardId, T exampleInstance, String... excludeProperty) {
    return super.findByExample(shardId, exampleInstance, excludeProperty);
  }
}
