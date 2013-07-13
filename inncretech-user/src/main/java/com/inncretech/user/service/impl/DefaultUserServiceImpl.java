package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserForgetPwd;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;
import java.util.Date;
import java.util.UUID;

@Service
public class DefaultUserServiceImpl implements UserService {

	@Override
	public User getUserById(Long userId, AccessContext accessContext) {
		return userDao.get(userId);
	}

	public User createUser(User user) {

		user.setId(userDao.getIdGenService().getNewUserId());
		userDao.createUser(user);
		return user;

	}

	public UserProfile createUserProfile(UserProfile userProfile) {
		userProfileDao.CreateUserProfile(userProfile);
		return userProfile;

	}

	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public void UpdateUserDet(User user) {
		User readUser = userDao.get(user.getId());
		readUser.setFirstName(user.getFirstName());
		readUser.setLastName(user.getLastName());
		userDao.UpdateUserDetails(readUser);

	}

	@Override
	@ShardAware(shardStrategy = "entityid", shardType = ShardType.USER)
	public UserProfile updateProfile(Long UserID, UserProfile profile) {
		UserProfile readProfile = userProfileDao.getProfileForUser(profile
				.getUserId());
		readProfile.setLongBio(profile.getLongBio());
		readProfile.setCurrentAddress(profile.getCurrentAddress());

		userProfileDao.save(profile.getUserId(), readProfile);
		return readProfile;

	}

	@Override
	public void updateFacebookInfo(String facebookId) {

	}

	@Override
	public UserForgetPwd forgotPassword(Long userID) {
		Date dt = new Date();
		String rndVal = UUID.randomUUID().toString();
		// sm.sendEmail();
		UserForgetPwd ufp = new UserForgetPwd();
		ufp.setUserId(userID);
		ufp.setRndString(rndVal);
		ufp.setDate(dt);

		return userFPDao.CreateRandomStringForPassword(ufp);

	}

	@Override
	public void resetPassword(String pwd) {
		User readUser = userDao.get(AccessContext.get().getCallerUserId());
		readUser.setPassword(pwd);
		userDao.UpdateUserDetails(readUser);

	}

	@Override
	public boolean validateRandomString(String randomString) {
		Date dt = new Date();
		UserForgetPwd ufp = new UserForgetPwd();
		ufp.setUserId(AccessContext.get().getCallerUserId());
		ufp.setRndString(randomString);
		ufp = userFPDao.GetDateForRandomString(ufp);
		if (dt.getTime() - ufp.getDate().getTime() < 86400) {
			return true;
		} else
			return false;
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	private UserFPDao userFPDao;

	@Override
	public User signupFacebookUser(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticateFbUserLogin(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticateUser(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Autowired
	// private SendMail sm;

}
