package com.inncretech.like;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.inncretech.like.model.LikeType;
import com.inncretech.like.model.SourceLike;
import com.inncretech.like.service.LikeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-like.xml" })
public class DefaultLikeServiceImplIntegrationTest extends BaseTest {

	@Autowired
	private LikeService likeService;
	@Autowired
	private TestUtil dbUtility;
	@Autowired
	SourceLikeDao srcDAO;

	@Test
	public void LikeSource() {

		long srcID = srcDAO.getIdGenService().getNewSourceId();
		long usrID = srcDAO.getIdGenService().getNewUserId();
		AccessContext.set(usrID, null);
		likeService.likeSource(srcID, LikeType.LIKE);
		
	}

	@Test
	public void UnLikeSource() {

		long srcID = srcDAO.getIdGenService().getNewSourceId();
		long usrID = srcDAO.getIdGenService().getNewUserId();
		AccessContext.set(usrID, null);
		likeService.likeSource(srcID, LikeType.UNLIKE);
		
	}

	@Test
	public void getLikeByObj() {

		long srcID = srcDAO.getIdGenService().getNewSourceId();
		long usrID = srcDAO.getIdGenService().getNewUserId();
		AccessContext.set(usrID, null);
		likeService.likeSource(srcID, LikeType.UNLIKE);
		List<SourceLike> lstSourceLike = likeService.getAllLikesBySource(srcID);

		 //assertEquals((byte)-1, (byte)lstSourceLike.get(0).getLikeValue());
		String result = lstSourceLike != null ? "Available" : "No Availability";
		assertEquals("Records Not found", "Available", result);

	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}
}