package com.inncretech.tag.service.impl;

import java.util.List;

import com.inncretech.core.test.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-tag.xml" })
@Service
public class DefaultTagServiceImplTest {

  @Autowired
  private TagService tagService;

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private TestUtil testUtil;

  @Test
  public void testTagSource() {
    String tag1Name = "test1-" + System.currentTimeMillis();
    String tag2Name = "test2-" + System.currentTimeMillis();
    Tag tag1 = tagService.createTag(tag1Name, 1L);
    Tag tag2 = tagService.createTag(tag2Name, 1L);
    Long sourceId = idGenerator.getNewSourceId();
    tagService.tagSourceInSourceShard(sourceId, idGenerator.getNewUserId(), tag1.getId());
    tagService.tagSourceInSourceShard(sourceId, idGenerator.getNewUserId(), tag2.getId());
    List<Tag> tagList = tagService.getTagsOfSource(sourceId);
    Assert.state(tagList.size() == 2);
    Assert.state(tagList.get(0).getName() != null);
    Assert.state(tagList.get(1).getName() != null);
    tagService.removeTagFromSourceInSourceShard(sourceId, tag1.getId());
    tagList = tagService.getTagsOfSource(sourceId);
    Assert.state(tagList.size() == 1);
    Assert.state(tagList.get(0).getName().equals(tag2Name));
    Tag tag = tagService.get(tagList.get(0).getId());
    Assert.state(tag.getName().equals(tag2Name));
    Tag tagSearchedWithName = tagService.getTagWithName(tag2Name);
    Assert.state(tagSearchedWithName.getName().equals(tag2Name));
  }

  @Test
  public void testGetTagsOfSource() {
    tagService.getTagsOfSource(idGenerator.getNewSourceId());
  }

  @Test
  public void testGetTagsCreatedByUser() {
    // Tag tag = tagService.createTag("test1", 1L);
    // List<Tag> taglist = tagService.getTagsCreatedByUser((long) 1);
    // System.out.println(taglist.size());
  }

  @Test
  public void testRemoveTagFromSource() {
    String tag1Name = "test2-" + System.currentTimeMillis();
    String tag2Name = "test3-" + System.currentTimeMillis();
    Tag tagOne = tagService.createTag(tag1Name, 1L);
    Tag tagTwo = tagService.createTag(tag2Name, 1L);
    Long sourceId = idGenerator.getNewSourceId();
    tagService.tagSourceInSourceShard(sourceId, tagOne.getCreatedBy(), tagOne.getId());
    tagService.tagSourceInSourceShard(sourceId, tagTwo.getCreatedBy(), tagTwo.getId());
    List<Tag> tags = tagService.getTagsOfSource(sourceId);
    System.out.println("Initially, Number of tags " + tags.size());
    tagService.removeTagFromSourceInSourceShard(sourceId, tagOne.getId());
    tags = tagService.getTagsOfSource(sourceId);
    System.out.println("Finally, Number of tags " + tags.size());
  }

  @Test
  public void testGetAllTags() {
    List<Tag> tags = tagService.getAllTags(0, 10);
    for (Tag tag : tags) {
      System.out.println("Id: " + tag.getId() + ", Name: " + tag.getName());
      System.out.println();
    }
    Assert.notEmpty(tags);
  }
}
