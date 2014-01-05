package com.inncretech.tag.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.tag.dao.TagDao;
import com.inncretech.tag.model.Tag;

@Component
public class TagDaoImpl implements TagDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Transactional
  public void createTag(Tag tag) {
    Long id = (Long) sessionFactory.getCurrentSession().save(tag);
    System.out.println("id::" + id);
    tag.setId(id);
  }

  @SuppressWarnings("unchecked")
  @Transactional
  public List<Tag> getAllTags(Integer offset, Integer maxLimit) {
    Query q = sessionFactory.getCurrentSession().createQuery("from Tag");
    q.setFirstResult(offset);
    q.setMaxResults(maxLimit);
    return q.list();
  }

  @SuppressWarnings("rawtypes")
  @Transactional
  public Tag getTag(String tagName) {
    Query q = sessionFactory.getCurrentSession().createQuery("from Tag where name = ? ").setParameter(0, tagName);
    List tagList = q.list();
    for (int i = 0; i < tagList.size(); i++)
      System.out.println();
    return (tagList != null && tagList.size() > 0) ? (Tag) tagList.get(0) : null;
  }

  @Transactional
  public Tag get(Long tagId) {
    return (Tag) sessionFactory.getCurrentSession().get(Tag.class, tagId);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Tag> getMatchingTags(String pattern, Boolean exact, Boolean startWith) {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
    if (exact) {
      criteria.add(Restrictions.eq("name", pattern));
    } else if (startWith) {
      pattern = pattern + "%";
      criteria.add(Restrictions.ilike("name", pattern));
    } else {
      pattern = "%" + pattern + "%";
      criteria.add(Restrictions.ilike("name", pattern));
    }
    return criteria.list();
  }

  @Override
  @Transactional
  public Tag getTagWithName(String tagName) {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
    criteria.add(Restrictions.eq("name", tagName));
    return (Tag) criteria.uniqueResult();
  }
}
