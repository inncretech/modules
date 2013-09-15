package com.inncretech.tag;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.tag.model.Tag;
import com.inncretech.tag.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-tag.xml" })
@Service
public class DefaultTagServiceImplIntegrationTest {

  @Autowired
  private TagService tagService;

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private TestIntegrationUtil dbUtility;

  private Long userId;

  @Test
  public void testTagSource() {
    Tag tag = tagService.createTag("test1", 1L);
    tagService.tagSource(idGenerator.getNewSourceId(), idGenerator.getNewUserId(), tag.getId());
  }

  @Test
  public void testGetTagsOfSource() {
    List<Tag> tagSourceList = tagService.getTagsOfSource(idGenerator.getNewSourceId());
    String result = tagSourceList != null ? "Available" : "No Availability";
    assertEquals("Records Not found", "Available", result);

  }


  @Test
  public void testRetrievalAndRemoveTagFromSource() {
    Long sourceId = idGenerator.getNewSourceId();
    Tag tagOne = tagService.createTag("test2", userId);
    Tag tagTwo = tagService.createTag("test3", userId);
    tagService.tagSource(sourceId, userId, tagOne.getId());
    tagService.tagSource(sourceId, userId, tagTwo.getId());
    List<Tag> tagsList = tagService.getTagsCreatedByUser(userId);
    String result = tagsList != null ? "Available" : "No Availability";
    assertEquals("Records Not found", "Available", result);
    tagService.removeTagFromSource(sourceId, (long) 36);
  }

  @Before
  public void setUp() {
    dbUtility.cleanUpdb();
    userId = idGenerator.getNewUserId();
  }
}
