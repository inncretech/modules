package com.inncretech.like;


import java.util.List;
import static org.junit.Assert.assertEquals;
import org.aspectj.apache.bcel.verifier.exc.AssertionViolatedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.test.TestUtil;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-like.xml" })
public class DefaultLikeServiceImplTest extends BaseTest{

  @Autowired
  private LikeService likeService;
  @Autowired
  private TestUtil dbUtility;
  @Autowired
  SourceLikeDao objDDAO ;
  
  @Test
  public void LikeSource() {
 
    long srcID= objDDAO.getIdGenService().getNewSourceId();
    long usrID =objDDAO.getIdGenService().getNewUserId();
    likeService.likeSource(srcID,usrID,new AccessContext());
    
  }
  @Test
  public void UnLikeSource() {
 
    long srcID= objDDAO.getIdGenService().getNewSourceId();
    long usrID =objDDAO.getIdGenService().getNewUserId();
    likeService.unLikeSource(srcID,usrID,new AccessContext());
    
  }
  @Test
  public void getLikeByObj() {
 
    long srcID= objDDAO.getIdGenService().getNewSourceId();
    long usrID =objDDAO.getIdGenService().getNewUserId();
    likeService.unLikeSource(srcID,usrID,new AccessContext());
    List<SourceLike> lstSourceLike =  likeService.getAllLikesByObject(srcID, new AccessContext());
    
    assertEquals((byte)-1, (byte)lstSourceLike.get(0).getLikeValue());
    
    
  }
  @Before
  public void setUp() {
    dbUtility.cleanUpdb();

  }
}

