package com.inncretech.core.sharding.dao;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T extends BaseEntity, PK extends Serializable> implements GenericDAO<T, PK>{

  private Class<T> clazz;

  @Autowired
  private SessionFactory sessionFactory;

  public GenericDAOImpl(Class<T> type) {
    this.clazz = type;
  }

  public Class<T> getPersistentClass() {
    return this.clazz;
  }

  public void delete(T persistentObject) {
    getSession().delete(persistentObject);
  }

  public void delete(PK id) {
    getSession().delete(load(id));
  }

  @SuppressWarnings("unchecked")
  public T load(PK id) {
    return (T) getSession().load(this.clazz, id);
  }

  @SuppressWarnings("unchecked")
  public T get(PK id) {
    return (T) getSession().get(this.clazz, id);
  }

  @SuppressWarnings("unchecked")
  public PK save(Long id, T o) {
    return (PK) getSession().save(o);
  }

  public void refresh(T o) {
    getSession().refresh(o);
  }

  public void saveOrUpdate(T o) {
    getSession().saveOrUpdate(o);
  }

  public void update(T o) {
    getSession().update(o);
  }

  public Query getQuery(String s) {
    return getSession().createQuery(s);
  }

  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(Criterion... criterion) {
    Criteria crit = getSession().createCriteria(getPersistentClass());

    for (Criterion c : criterion) {
      crit.add(c);
    }
    return crit.list();
  }

  public List<T> findAll() {
    return findByCriteria();
  }

  public void evict(T obj) {
    getSession().evict(obj);
  }

  @SuppressWarnings("unchecked")
  public List<T> findByExample(T exampleInstance, String... excludeProperty) {
    Criteria crit = getSession().createCriteria(getPersistentClass());
    Example example = Example.create(exampleInstance);
    for (String exclude : excludeProperty) {
      example.excludeProperty(exclude);
    }
    crit.add(example);
    return crit.list();
  }


  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
