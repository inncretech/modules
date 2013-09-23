package com.inncretech.core.sharding.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.model.ShardEntity;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;

/**
 * Generic DAO class.
 * 
 * @author shade05
 */
public interface GenericSourceShardDAO<T extends BaseEntity, PK extends Serializable> {

  /**
   * Persist the newInstance object into database.
   * 
   **/
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  PK save(PK id, T newInstance);

  /**
   * Save or update.
   * 
   * @param transientObject
   *          to save
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void saveOrUpdate(T transientObject);

  /**
   * Retrieve a persisted object with a given id from the database.
   * 
   * @param id
   *          to load
   * @return An object of type T
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  T load(PK id);

  /**
   * Retrieve a persisted object with a given id from the database.
   * 
   * @param id
   *          to get
   * @return An object of type T
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  T get(PK id);

  /**
   * Save changes made to a persistent object.
   * 
   * @param transientObject
   *          object to update
   **/
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void update(T transientObject);

  /**
   * Remove the given object from persistent storage in the database.
   * 
   * @param persistentObject
   *          object to delete.
   **/
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void delete(T persistentObject);

  /**
   * Remove the given object from persistent storage in the database.
   * 
   * @param s
   *          Query to execute
   * @return A query object
   **/
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  Query getQuery(Integer shardId, String s);

  /**
   * Deletes an object of a given Id. Will load the object internally so
   * consider using delete (T obj) directly.
   * 
   * @param id
   *          Id of record
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void delete(PK id);

  /**
   * Refreshes the object of type T.
   * 
   * @param persistentObject
   *          to refresh
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void refresh(T persistentObject);

  /**
   * FindByExample.
   * 
   * @param exampleInstance
   *          to use
   * @param excludeProperty
   *          to exclude
   * @return A list of objects
   */
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  List<T> findByExample(Integer shardId, T exampleInstance, String... excludeProperty);

  /**
   * Returns a list of objects.
   * 
   * @return list of objects
   */
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  List<T> findAll(Integer shardId);

  /**
   * Object to evict from cache.
   * 
   * @param obj
   *          Object to evict
   */
  @ShardAware(shardStrategy = "entityid", shardType = ShardType.SOURCE)
  void evict(T obj);

  /**
   * Hibernate wrapper.
   * 
   * @param criterion
   *          to filter.
   * @return list of objects
   */
  @ShardAware(shardStrategy = "shardid", shardType = ShardType.SOURCE)
  List<T> findByCriteria(Integer shardId, Criterion... criterion);

  /**
   * Return the currently set class.
   * 
   * @return the currently set class.
   */
  Class<T> getPersistentClass();

  public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds);
}
