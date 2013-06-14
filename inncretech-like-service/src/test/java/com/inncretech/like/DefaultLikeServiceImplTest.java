package com.inncretech.like;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.test.TestUtil;
import com.inncretech.like.service.LikeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-like.xml" })
public class DefaultLikeServiceImplTest extends BaseTest{

  @Autowired
  private LikeService likeService;

  
  @Test
  public void LikeSource() {
 
    likeService.likeSource((long) 2,(long) 3,new AccessContext());
    
  }

}

