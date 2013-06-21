package com.inncretech.tag.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.tag.model.Tag;

@Component
public class TagDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Transactional
  public void createTag(Tag tag) {
    Long id = (Long)sessionFactory.getCurrentSession().save(tag);
    System.out.println("id::"+id);
    tag.setId(id);
  }

  @Transactional
  public Tag getTag(String tagName) {
    Query q = sessionFactory.getCurrentSession().createQuery("from Tag where name = ? ").setParameter(0, tagName);
    List tagList = q.list();
    for(int i =0;i<tagList.size();i++)
    	System.out.println();
    return (tagList.size() > 0) ? (Tag) tagList.get(0) : null;
  }
  
  public Tag get(Long tagId) {
    return (Tag)sessionFactory.getCurrentSession().get(Tag.class, tagId);
  }

}
