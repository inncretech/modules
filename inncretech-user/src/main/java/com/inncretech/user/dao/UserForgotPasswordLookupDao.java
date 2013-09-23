package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericDAO;
import com.inncretech.user.model.UserForgotPasswordLookup;

public interface UserForgotPasswordLookupDao extends GenericDAO<UserForgotPasswordLookup, Long>{

  Long getUserId(String key);
}
