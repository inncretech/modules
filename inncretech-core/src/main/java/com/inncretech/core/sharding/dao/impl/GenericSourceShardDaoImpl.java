package com.inncretech.core.sharding.dao.impl;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.AbstractShardAwareHibernateDao;
import com.inncretech.core.sharding.dao.GenericSourceShardDAO;
import com.inncretech.core.sharding.dao.MultiShardSourceDao;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
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
public abstract class GenericSourceShardDaoImpl<T extends BaseEntity, PK extends Serializable> extends AbstractShardAwareHibernateDao<T, PK>
    implements GenericSourceShardDAO<T, PK> {

  @Autowired
  private MultiShardSourceDao multiShardSourceDao;

  public GenericSourceShardDaoImpl(Class<T> type) {
    super(type, ShardType.SOURCE);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void delete(T persistentObject) {
    super.delete(persistentObject);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void delete(PK id) {
    super.delete(id);
  }

  @Override
  public T load(PK id) {
    return super.load(id);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public T get(PK id) {
    return super.get(id);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public PK save(T o) {
    return super.save(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void refresh(T o) {
    super.refresh(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void saveOrUpdate(T o) {
    super.saveOrUpdate(o);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void update(T o) {
    super.update(o);
  }

  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  public Query getQuery(Integer shardId, String s) {
    return super.getQuery(shardId, s);
  }

  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  public List<T> findByCriteria(Integer shardId, Criterion... criterion) {
    return super.findByCriteria(shardId, criterion);
  }

  @SuppressWarnings("unchecked")
  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  public List<T> findByCriteria(Integer shardId, DetachedCriteria detachedCriteria) {
    return multiShardSourceDao.findByCriteria(shardId, detachedCriteria);
  }

  @Override
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  public List<T> findAll(Integer shardId) {
    return super.findAll(shardId);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public void evict(T obj) {
    super.evict(obj);
  }

  @Override
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  public List<T> findByExample(Integer shardId, T exampleInstance, String... excludeProperty) {
    return super.findByExample(shardId, exampleInstance, excludeProperty);
  }
}
