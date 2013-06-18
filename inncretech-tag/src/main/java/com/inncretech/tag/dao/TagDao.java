package com.inncretech.tag.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.tag.model.Tag;

/**
 * 
 * @author amit
 * 
 */
@Component
public class TagDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param tag
	 */
	@Transactional
	public void createTag(Tag tag) {

		if (sessionFactory == null) {
			throw new IllegalStateException();
		}
		System.out.println("Tag ID=" + tag.getId() + ",Tag Name="
				+ tag.getName());

		sessionFactory.getCurrentSession().save(tag);

	}

}
