package com.inncretech.user.dao.impl;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.dao.impl.GenericDAOImpl;
import com.inncretech.user.model.UserForgotPassword;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.inncretech.user.dao.UserFPDao;

import java.util.List;

@Component
public class UserFPDaoImpl extends GenericDAOImpl<UserForgotPassword, Long> implements UserFPDao {

  public UserFPDaoImpl() {
    super(UserForgotPassword.class);
  }

  public UserForgotPassword getDateForRandomString(String obj) {
    Query q = getSession().createQuery("from UserForgotPassword where rndString = ? and recordStatus = ?");
    q.setString(0, obj);
    q.setByte(1, RecordStatus.ACTIVE.getId());
    List<UserForgotPassword> result = q.list();
    if(result.size() > 0)
      return result.get(0);
    else
      return null;
  }

  public List<UserForgotPassword> getByUserId(Long userId){
    Query q = getSession().createQuery("from UserForgotPassword where userId = ? and recordStatus = ?");
    q.setLong(0, userId);
    q.setByte(1, RecordStatus.ACTIVE.getId());
    return q.list();
  }
}
