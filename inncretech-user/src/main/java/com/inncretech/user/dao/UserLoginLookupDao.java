package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericDAO;
import com.inncretech.user.model.UserLoginLookup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserLoginLookupDao extends GenericDAO<UserLoginLookup , Long>{

  @Transactional
  UserLoginLookup getUserLoginLookupByEmail(String login);

  @Transactional
  UserLoginLookup getUserLoginLookupByTwitterId(String twitterId);


    @Transactional
  void deactiveEmail(Long userId);

  @Transactional
  List<UserLoginLookup> getUserLoginLookups(List<String> logins);

  @Transactional
  UserLoginLookup getUserLoginLookupByLoginId(String loginId);
  
  @Transactional
  UserLoginLookup getUserLoginLookupByUserId(Long userId);
}
