package com.inncretech.core.sharding.dao;

import com.inncretech.core.model.BaseEntity;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public interface GenericDAO<T extends BaseEntity, PK extends Serializable> {

  PK save(T newInstance);

  void saveOrUpdate(T transientObject);

  T load(PK id);

  T get(PK id);

  void update(T transientObject);

  void delete(T persistentObject);

  Query getQuery(String s);

  void delete(PK id);

  void refresh(T persistentObject);

  List<T> findByExample(T exampleInstance, String... excludeProperty);

  List<T> findAll();

  void evict(T obj);

  List<T> findByCriteria(Criterion... criterion);

  Class<T> getPersistentClass();
}
