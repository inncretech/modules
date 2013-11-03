package com.inncretech.like;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.BaseTest;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.like.dao.SourceLikeDao;
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-like.xml" })
public class DefaultLikeServiceImplTest extends BaseTest {

  @Autowired
  private LikeService likeService;

  @Autowired
  private TestUtil dbUtility;

  @Autowired
  SourceLikeDao srcDAO;

  @Autowired
  private IdGenerator idGenerator;
  
  @Test
  public void LikeSource(){
  	
  	long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    likeService.likeSource(srcID, LikeType.LIKE, usrID);
    
  }

  @Test
  public void miscTests() {

    long srcID1 = idGenerator.getNewSourceId();
    long srcID2 = idGenerator.getNewSourceId();
    long srcID3 = idGenerator.getNewSourceId();
    long srcID4 = idGenerator.getNewSourceId();
    long usrID1 = idGenerator.getNewUserId();
    long usrID2 = idGenerator.getNewUserId();
    long usrID3 = idGenerator.getNewUserId();
    likeService.likeSource(srcID1, LikeType.LIKE, usrID1);
    likeService.likeSource(srcID2, LikeType.LIKE, usrID1);
    likeService.likeSource(srcID3, LikeType.LIKE, usrID1);
    likeService.likeSource(srcID4, LikeType.LIKE, usrID1);   
    likeService.likeSource(srcID1, LikeType.UNLIKE, usrID1);
    likeService.likeSource(srcID1, LikeType.LIKE, usrID1);
    
    List<SourceLike> sourceLikeList = likeService.getAllLikeByUser(usrID1);
    System.out.println("Number in SourceLike (6) "+sourceLikeList.size());
    
    sourceLikeList = likeService.getAllLikesBySource(srcID1);
    System.out.println("Number in SourceLike (3) "+sourceLikeList.size());

   
    likeService.likeSource(srcID2, LikeType.LIKE, usrID2);
    likeService.likeSource(srcID2, LikeType.LIKE, usrID3);
    likeService.likeSource(srcID2, LikeType.UNLIKE, usrID3);
    sourceLikeList = likeService.getAllLikesBySource(srcID2);
    System.out.println("Number in SourceLike (4) "+sourceLikeList.size());
  }

  @Test
  public void UnLikeSource() {

    long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    likeService.likeSource(srcID, LikeType.UNLIKE, usrID);

  }

  @Test
  public void getLikeByObj() {

    long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    likeService.likeSource(srcID, LikeType.UNLIKE, usrID);
    List<SourceLike> lstSourceLike = likeService.getAllLikesBySource(srcID);

    assertEquals((byte) -1, (byte) lstSourceLike.get(0).getLikeValue());

  }

  @Test
  public void getLikeByUser() {

    long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    likeService.likeSource(srcID, LikeType.UNLIKE, usrID);
    List<SourceLike> lstSourceLike = likeService.getAllLikeByUser(usrID);

    assertEquals((byte) -1, (byte) lstSourceLike.get(0).getLikeValue());

  }

//  @Before
//  public void setUp() {
//    dbUtility.cleanUpdb(new String[] { "like" });
//  }
}
