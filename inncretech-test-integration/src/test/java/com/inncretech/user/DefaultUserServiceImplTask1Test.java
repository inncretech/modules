package com.inncretech.user;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.follow.service.FollowService;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

//created 1500 users, can be increased by changing noOfUsers given below
//total 3 Followers  
//serial execution

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml",
		"/applicationcontext-follow.xml" })
public class DefaultUserServiceImplTask1Test {

	@Autowired
	private UserService userService;
	@Autowired
	private TestUtil dbUtility;
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private FollowService followService;

	private long noOfUsers = 50;
	private long totalFollowers = 3;

	Long randomeValue = null;
	Logger logger = Logger.getLogger(DefaultUserServiceImplTask1Test.class
			.getName());
	public List randomList = new ArrayList();

	@SuppressWarnings("unchecked")
	@Test
	public void createUser() throws Exception {
		User usr = null;
		List list = new ArrayList<User>();
		for (int userCount = 1; userCount < totalFollowers + 1; userCount++) {

			for (int i = 1; i <= noOfUsers; i++) {
			usr = createTestUser("Devrun", "Test", "test@gmail.com",
						"devuser_" + i, "dev_" + i, "dev_" + i + "@facebook",
						"dev_" + i + "@twitter", "dev_" + i + "@gooogle");
				User user = userService.createUser(usr);
				list.add(user.getId());

			}

			int index = new Random().nextInt(list.size());
			Long randomValue = (Long) list.get(index);

			randomList.add(randomValue);
		}
		testFollowUser(randomList);
		//find the list of followers
		testFollowersList(randomList);
		
	}

	public void testFollowUser(List randomList) {
		System.out.println("Random Followers List:" + randomList);
		Long userId = null;
		for (int i = 1; i < randomList.size(); i++) {
			randomList.get(i);
			System.out.println("userId:" + randomList.get(i).toString());
			userId = new Long(randomList.get(i).toString());
			System.out.println("UserId Passed to DB:"+userId);
			followService.followUser(idGenerator.getNewUserId(),userId);
		}
		//assertNotNull("followService is null", followService.getClass());

	}
 
	public List testFollowersList(List userList){
		List followerList=null;
		System.out.println("Size of UserList:"+userList.size());
		// getFollowersByUser
				for (int i = 1; i < userList.size(); i++) {
					Long userId= new Long(userList.get(i).toString());
					System.out.println("user id to find the list of followers="+userId);
					followerList=followService.getFollowersByUser(userId);
										
				}
				//System.out.println("Just test..."+followerList.size());
				if(followerList!=null){
					System.out.println("Available Followers for the User >>>>"+followerList.size());
					return followerList;
				}
				return null;
				
	}
	
	@Before
	public void setUp() {
	  String[] tablesToBeDeleted = {"user"};
		dbUtility.cleanUpdb(tablesToBeDeleted);

	}

	User createTestUser(String fName, String lName, String eMail, String uName,
			String password, String facebookId, String twitterId,
			String googleId) {
		User usr = new User();
		usr.setFirstName(fName);
		usr.setLastName(lName);
		usr.setEmail(eMail);
		usr.setUserName(uName);
		usr.setPassword(password);
		usr.setFacebookId(facebookId);
		usr.setTwitterId(twitterId);
		usr.setGoogleId(googleId);
		return usr;

	}

	UserProfile CreateTestProfile(String shortBio, String longBio) {
		UserProfile usrprf = new UserProfile();
		usrprf.setLongBio(longBio);
		usrprf.setShortBio(shortBio);
		return usrprf;
	}

}
