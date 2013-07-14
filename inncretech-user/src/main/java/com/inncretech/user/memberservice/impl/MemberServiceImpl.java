package com.inncretech.user.memberservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.inncretech.user.dao.UserDao;
import com.inncretech.user.memberservice.MemberService;
import com.inncretech.user.model.User;



@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User signupFacebookUser(String accessToken) {
		FacebookTemplate fbTemplate = new FacebookTemplate(accessToken);
		UserOperations userOperation = fbTemplate.userOperations();
		FacebookProfile profile = userOperation.getUserProfile();

		String email = profile.getEmail();
		System.out.println(email);
		
		List<User> user = userDao.getUserByEmail(email);
		
		System.out.println(user.size());
		
		/*Member member = memberDao.getMemberByEmail(email);
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
		return resultMember;*/
		return null;
	}
/*	
	public MemberDTO signupFacebookUser(String accessToken) {
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
