package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericDAO;
import com.inncretech.user.model.UserLoginLookup;
import org.springframework.transaction.annotation.Transactional;

public interface UserLoginLookupDao extends GenericDAO<UserLoginLookup , Long>{

  @Transactional
  UserLoginLookup getUserLoginLookup(String login);
}
