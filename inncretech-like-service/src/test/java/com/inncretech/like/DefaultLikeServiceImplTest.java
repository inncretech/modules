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
  public void LikeSource() {

    long srcID = idGenerator.getNewSourceId();
    long usrID = idGenerator.getNewUserId();
    likeService.likeSource(srcID, LikeType.LIKE, usrID);

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

  @Before
  public void setUp() {
    dbUtility.cleanUpdb(new String[] { "like" });
  }
}
