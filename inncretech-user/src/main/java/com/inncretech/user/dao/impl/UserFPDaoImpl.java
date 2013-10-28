package com.inncretech.user.dao.impl;

import com.inncretech.core.sharding.dao.impl.GenericDAOImpl;
import com.inncretech.user.model.UserForgotPassword;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.user.dao.UserFPDao;

@Component
public class UserFPDaoImpl extends GenericDAOImpl<UserForgotPassword, Long> implements UserFPDao {

  public UserFPDaoImpl() {
    super(UserForgotPassword.class);
  }

  public UserForgotPassword getDateForRandomString(UserForgotPassword obj) {
    Query q = getSession().createQuery("from UserForgotPassword where rndString = ?");
    q.setString(0, obj.getRndString());
    return (UserForgotPassword) q.uniqueResult();
  }
}
