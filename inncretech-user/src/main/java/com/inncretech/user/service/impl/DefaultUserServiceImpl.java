package com.inncretech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.ShardAware;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.dao.UserFPDao;
import com.inncretech.user.dao.UserProfileDao;
import com.inncretech.user.memberservice.MemberService;
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
    UserProfile readProfile = userProfileDao.getProfileForUser(profile.getUserId());
    readProfile.setLongBio(profile.getLongBio());
    readProfile.setCurrentAddress(profile.getCurrentAddress());

    userProfileDao.save(profile.getUserId(), readProfile);
    return readProfile;

  }


  @Override
  public UserForgetPwd forgotPassword(Long userID) {
    Date dt = new Date(); 
   String rndVal =UUID.randomUUID().toString();
   // sm.sendEmail();
   UserForgetPwd ufp= new UserForgetPwd();
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
  public  boolean validateRandomString(String randomString)
  {
    Date dt = new Date();
    UserForgetPwd ufp = new UserForgetPwd();
    ufp.setUserId(AccessContext.get().getCallerUserId());
    ufp.setRndString(randomString);
    ufp  = userFPDao.GetDateForRandomString(ufp);
    if(dt.getTime() -  ufp.getDate().getTime() < 86400)
    {
      return true;
    }
    else
      return false;
    }
    
  @Override
  public void updateFacebookInfo(String facebookId) {


  }    

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserProfileDao userProfileDao;
  
  @Autowired
  private UserFPDao userFPDao;
  
  @Autowired
  private MemberService memberService;

@Override
public User signupFacebookUser(String accessToken) {
	 User member = memberService.signupFacebookUser(accessToken);
	   /* doAutoFbLogin(member, accessToken, req);*/
	 
	    return null;
}

/*public MemberDTO signupFacebookUser(String accessToken) {
	FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
	UserOperations userOperation = fbTemplate.userOperations();
	FacebookProfile profile = userOperation.getUserProfile();

	String email = profile.getEmail();
	Member member = memberDao.getMemberByEmail(email);
	MemberDTO resultMember = null;
	if (member == null) {
		resultMember = createMemberFromFacebookProfile(profile);
	} else {
		  MemberInfo memberInfo = memberInfoDao.getMemberInfoByMemberId(member.getId());
			member.setFacebookId(profile.getId());
			memberInfo.setFacebookId(profile.getUsername());
			memberDao.save(member);
			memberInfoDao.save(memberInfo);
		resultMember = getMember(member.getId());
	}
	updateFacebookAuthtoken( profile,  accessToken);
	return resultMember;
}
*/
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
}
