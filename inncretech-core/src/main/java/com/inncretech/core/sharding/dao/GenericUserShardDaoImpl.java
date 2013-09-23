package com.inncretech.core.sharding.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.inncretech.core.model.ShardEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.model.BaseEntity;
import com.inncretech.core.sharding.HibernateSessionFactoryManager;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;

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

public abstract class GenericUserShardDaoImpl<T extends BaseEntity, PK extends Serializable> implements GenericUserShardDAO<T, PK> {

	private Class<T> clazz;

	private ShardType shardType;

	@Autowired
	private HibernateSessionFactoryManager sessionFactoryManager;

	@Autowired
	private IdGenerator idGenService;

	public GenericUserShardDaoImpl(Class<T > type) {
		this.clazz = type;
		this.shardType = ShardType.USER;
	}

	public Class<T> getPersistentClass() {
		return this.clazz;
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void delete(T persistentObject) {
		getSession(persistentObject.getId()).delete(persistentObject);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void delete(PK id) {
		getSession((Long) id).delete(load(id));
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		return (T) getSession((Long) id).load(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public T get(PK id) {
		return (T) getSession((Long) id).get(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public PK save(Long id, T o) {
		return (PK) getSession(id).save(o);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void refresh(T o) {
		getSession(o.getId()).refresh(o);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void saveOrUpdate(T o) {
		getSession(o.getId()).saveOrUpdate(o);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void update(T o) {
		getSession(o.getId()).update(o);
	}

	@ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
	public Query getQuery(Integer shardId, String s) {
		return getCurrentSessionByShard(shardId).createQuery(s);
	}

	@SuppressWarnings("unchecked")
	@ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
	public List<T> findByCriteria(Integer shardId, Criterion... criterion) {
		Criteria crit = getCurrentSessionByShard(shardId).createCriteria(getPersistentClass());

		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
	public List<T> findAll(Integer shardId) {
		return findByCriteria(shardId);
	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void evict(T obj) {
		getSession(obj.getId()).evict(obj);
	}

	@SuppressWarnings("unchecked")
	@ShardAware(shardStrategy = "shardid", shardType = ShardType.USER)
	public List<T> findByExample(Integer shardId, T exampleInstance, String... excludeProperty) {
		Criteria crit = getCurrentSessionByShard(shardId).createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public Session getCurrentSessionByShard(Integer shardId) {
		SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
		return sessionFactory.getCurrentSession();
	}

	public Map<Integer, List<Long>> bucketizeEntites(List<Long> entityIds) {
		return idGenService.bucketizeEntites(entityIds, getShardType());
	}

	public ShardType getShardType() {
		return shardType;
	}

	public Session getSession(Long entityId) {
		Integer shardId = idGenService.getShardId(entityId, getShardType());
		SessionFactory sessionFactory = sessionFactoryManager.getSessionFactory(shardId);
		return sessionFactory.getCurrentSession();
	}
}
