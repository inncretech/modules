package com.inncretech.tag.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author amit
 * 
 */
@Component
public class SourceTagDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param sourceId
	 * @return
	 */
	@Transactional
	public List<Object> getTagsOfSource(Long sourceId) {
		if (sessionFactory == null) {
			throw new IllegalStateException();
		}

		Query query = sessionFactory.getCurrentSession()
				.createQuery("from SourceTag where sourceId= :id")
				.setParameter("id", sourceId);

		@SuppressWarnings("unchecked")
		List<Object> tagsOfSource = query.list();
		System.out.println("Source tags list:" + tagsOfSource.size());

		return tagsOfSource;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<Object> getTagsCreatedByUser(Long userId) {
		if (sessionFactory == null) {
			throw new IllegalStateException();
		}

		Query query = sessionFactory.getCurrentSession()
				.createQuery("from SourceTag where userId=:user_id")
				.setParameter("user_id", userId);

		@SuppressWarnings("unchecked")
		List<Object> tagsCreatedByUser = query.list();
		System.out.println("Source tags list by user:"
				+ tagsCreatedByUser.size());

		return tagsCreatedByUser;
	}

	/**
	 * 
	 * @param sourceId
	 * @param tagId
	 * @return
	 */
	@Transactional
	public int removeTagFromSource(Long sourceId, Long tagId) {
		if (sessionFactory == null) {
			throw new IllegalStateException();
		}
		Session sess = this.sessionFactory.getCurrentSession();

		int count = sess
				.createSQLQuery(
						"delete from source_tag where source_id= :source_id")
				.setParameter("source_id", sourceId).executeUpdate();

		System.out.println("Source tag delete Count:" + count);
		return count;
	}

}
