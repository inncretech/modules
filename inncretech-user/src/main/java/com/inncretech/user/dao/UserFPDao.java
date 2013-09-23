package com.inncretech.user.dao;

import com.inncretech.core.sharding.dao.GenericDAO;
import com.inncretech.user.model.UserForgotPassword;

public interface UserFPDao extends GenericDAO<UserForgotPassword, Long> {

  public UserForgotPassword getDateForRandomString(UserForgotPassword obj);
}
