package com.inncretech.source;

import com.inncretech.core.sharding.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.source.model.Source;
import com.inncretech.source.service.SourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-source.xml" })
public class SourceServiceTest extends BaseTest {

  @Autowired
  private SourceService sourceService;

  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void testSourceCreate() {
    Source s = new Source();
    s.setId(idGenerator.getNewSourceId());
    s.setSourceType(1);
    s.setSourceUri("testsource1");
   
    sourceService.create(s);
  }
}
