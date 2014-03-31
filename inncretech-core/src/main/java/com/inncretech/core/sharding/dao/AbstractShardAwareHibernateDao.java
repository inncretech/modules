package com.inncretech.core.sharding.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.inncretech.core.model.IdEntity;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardType;

public class AbstractShardAwareHibernateDao<T extends IdEntity, PK extends Serializable> {

  private Class<T> clazz = null;

  private ShardType shardType = null;

  public AbstractShardAwareHibernateDao(Class<T> clazz, ShardType shardType) {
    this.clazz = clazz;
    this.shardType = shardType;
  }

  @Autowired
  private ShardAwareDaoUtil shardAwareDaoUtil;

  public Session getCurrentSessionByShard(Integer shardId) {
    return shardAwareDaoUtil.getCurrentSessionByShard(shardId);
  }

  public IdGenerator getIdGenService() {
    return shardAwareDaoUtil.getIdGenService();
  }

  public void delete(T persistentObject) {
    getSession(persistentObject.getId()).delete(persistentObject);
  }

  public void delete(PK id) {
    getSession((Long) id).delete(load(id));
  }

  @SuppressWarnings("unchecked")
  public T load(PK id) {
    return (T) getSession((Long) id).load(this.clazz, id);
  }

  @SuppressWarnings("unchecked")
  public T get(PK id) {
    return (T) getSession((Long) id).get(this.clazz, id);
  }

  public  Map<Long, T> get(List<Long> ids){
    Map<Integer, List<Long>> entityBucket = bucketizeEntites(ids);
    Map<Long, T> result = new HashMap<Long, T>();
    for(Map.Entry<Integer, List<Long>> entry : entityBucket.entrySet()){
      List<T> entities = shardAwareDaoUtil.getEntities(entry.getKey(), clazz.getName() , entry.getValue());
      for(T entity : entities){
        result.put(entity.getId() , entity);
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public PK save(T o) {
    return (PK) getSession(o.getId()).save(o);
  }

  public void refresh(T o) {
    getSession(o.getId()).refresh(o);
  }

  public void saveOrUpdate(T o) {
    getSession(o.getId()).saveOrUpdate(o);
  }

  public void update(T o) {
    getSession(o.getId()).update(o);
  }

  public Query getQuery(Integer shardId, String s) {
    return shardAwareDaoUtil.getCurrentSessionByShard(shardId).createQuery(s);
  }

  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(Integer shardId, Criterion... criterion) {
    Criteria crit = shardAwareDaoUtil.getCurrentSessionByShard(shardId).createCriteria(clazz);

    for (Criterion c : criterion) {
      crit.add(c);
    }
    return crit.list();
  }

  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(Integer shardId, Integer offset, Integer limit, Criterion... criterion) {
    Criteria crit = shardAwareDaoUtil.getCurrentSessionByShard(shardId).createCriteria(clazz);

    for (Criterion c : criterion) {
      crit.add(c);
    }
    return crit.setFirstResult(offset).setMaxResults(limit).list();
  }
  
  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(Integer shardId, Pageable pageable, Criterion... criterion) {
    Criteria crit = shardAwareDaoUtil.getCurrentSessionByShard(shardId).createCriteria(clazz);

    for (Criterion c : criterion) {
      crit.add(c);
    }
    Sort sort = pageable.getSort();
    if (sort != null) {
      Iterator<org.springframework.data.domain.Sort.Order> iterator = sort.iterator();
      while (iterator.hasNext()) {
        org.springframework.data.domain.Sort.Order next = iterator.next();
        if (next.getDirection() == Direction.ASC) {
          crit.addOrder(Order.asc(next.getProperty()));
        } else {
          crit.addOrder(Order.desc(next.getProperty()));
        }
      }
    }
    return crit.setFirstResult(pageable.getPageNumber() * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).list();
  }

  public List<T> findAll(Integer shardId) {
    return findByCriteria(shardId);
  }

  public void evict(T obj) {
    getSession(obj.getId()).evict(obj);
  }

  @SuppressWarnings("unchecked")
  public List<T> findByExample(Integer shardId, T exampleInstance, String... excludeProperty) {
    Criteria crit = shardAwareDaoUtil.getCurrentSessionByShard(shardId).createCriteria(clazz);
    Example example = Example.create(exampleInstance);
    for (String exclude : excludeProperty) {
      example.excludeProperty(exclude);
    }
    crit.add(example);
    return crit.list();
  }

  public ShardType getShardType() {
    return shardType;
  }

  public Session getSession(Long entityId) {
    Integer shardId = shardAwareDaoUtil.getIdGenService().getShardId(entityId, getShardType());
    SessionFactory sessionFactory = shardAwareDaoUtil.getSessionFactoryManager().getSessionFactory(shardId);
    return sessionFactory.getCurrentSession();
  }

  public Class<T> getPersistentClass() {
    return clazz;
  }

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds) {
    return shardAwareDaoUtil.getIdGenService().bucketizeEntites(entityIds, getShardType());
  }
}
