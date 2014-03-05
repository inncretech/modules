package com.inncretech.user.dao.impl;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.dao.impl.GenericDAOImpl;
import com.inncretech.user.dao.UserLoginLookupDao;
import com.inncretech.user.model.UserLoginLookup;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserLoginLookupDaoImpl extends GenericDAOImpl<UserLoginLookup , Long> implements UserLoginLookupDao{
  public UserLoginLookupDaoImpl(){
    super(UserLoginLookup.class);
  }

  @Transactional
  public UserLoginLookup getUserLoginLookupByEmail(String email){
    Query query = getQuery("from UserLoginLookup where email=:email and recordStatus != :recordStatus");
    query.setParameter("email", email);
    query.setParameter("recordStatus", RecordStatus.INACTIVE.getId());
    UserLoginLookup userLoginLookup =null;
    @SuppressWarnings("unchecked")
    List<UserLoginLookup> results = query.list();
    if(results.size() > 0)
      userLoginLookup = results.get(0);

    return userLoginLookup;
  }

  @Transactional
  public void deactiveEmail(Long userId){
    Query query = getQuery("update UserLoginLookup set recordStatus= :recordStatus where userId=:userId");
    query.setParameter("userId", userId);
    query.setParameter("recordStatus", RecordStatus.INACTIVE.getId());
    query.executeUpdate();
  }

  @Transactional
  @SuppressWarnings({ "unused", "unchecked" })
  public List<UserLoginLookup> getUserLoginLookups(List<String> logins){
    Query query = getQuery("from UserLoginLookup where loginId in (:logins)");
    query.setParameterList("logins", logins);
    UserLoginLookup userLoginLookup =null;
    return query.list();
  }

  @Override
  @Transactional
  public UserLoginLookup getUserLoginLookupByLoginId(String loginId) {
    Query query = getQuery("from UserLoginLookup where loginId=:loginId and recordStatus != :recordStatus");
    query.setParameter("loginId", loginId);
    query.setParameter("recordStatus", RecordStatus.INACTIVE.getId());
    UserLoginLookup userLoginLookup =null;
    @SuppressWarnings("unchecked")
    List<UserLoginLookup> results = query.list();
    if(results.size() > 0)
      userLoginLookup = results.get(0);

    return userLoginLookup;
  }
}
