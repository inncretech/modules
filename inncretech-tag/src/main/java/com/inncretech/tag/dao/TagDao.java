package com.inncretech.tag.dao;

import java.util.List;

import com.inncretech.tag.model.Tag;

public interface TagDao {

  public void createTag(Tag tag);

  public List<Tag> getAllTags(int offset, int maxLimit);

  public Tag getTag(String tagName);

  public Tag get(Long tagId);

  public List<Tag> getMatchingTags(String pattern, boolean exactMatch, boolean startWith);
}
