package com.inncretech.follow.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestUtil {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void cleanUpdb() {

		if (sessionFactory == null) {
			throw new IllegalStateException();
		}

		Session sess = this.sessionFactory.getCurrentSession();

		String[] tablesToBeDeleted = { "follow_source", "follow_tag",
				"follow_user" };
		for (String table : tablesToBeDeleted)
			sess.createSQLQuery("delete from " + table).executeUpdate();

	}

}
