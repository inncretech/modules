package com.inncretech.core.sharding.dao.impl;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.sharding.dao.GenericDAO;

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

  @Override
  public Class<T> getPersistentClass() {
    return this.clazz;
  }

  @Override
  public void delete(T persistentObject) {
    getSession().delete(persistentObject);
  }

  @Override
  public void delete(PK id) {
    getSession().delete(load(id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public T load(PK id) {
    return (T) getSession().load(this.clazz, id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(PK id) {
    return (T) getSession().get(this.clazz, id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public PK save(T o) {
    return (PK) getSession().save(o);
  }

  @Override
  public void refresh(T o) {
    getSession().refresh(o);
  }

  @Override
  public void saveOrUpdate(T o) {
    getSession().saveOrUpdate(o);
  }

  @Override
  public void update(T o) {
    getSession().update(o);
  }

  @Override
  public Query getQuery(String s) {
    return getSession().createQuery(s);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> findByCriteria(Criterion... criterion) {
    Criteria crit = getSession().createCriteria(getPersistentClass());

    for (Criterion c : criterion) {
      crit.add(c);
    }
    return crit.list();
  }

  @Override
  public List<T> findAll() {
    return findByCriteria();
  }

  @Override
  public void evict(T obj) {
    getSession().evict(obj);
  }

  @Override
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
