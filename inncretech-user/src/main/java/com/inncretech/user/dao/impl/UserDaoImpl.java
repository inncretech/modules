package com.inncretech.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.core.model.RecordStatus;
import com.inncretech.core.sharding.ShardType;
import com.inncretech.core.sharding.dao.ShardConfigDao;
import com.inncretech.core.sharding.dao.impl.GenericUserShardDaoImpl;
import com.inncretech.core.sharding.model.ShardConfig;
import com.inncretech.user.dao.UserDao;
import com.inncretech.user.model.User;

@Component
public class UserDaoImpl extends GenericUserShardDaoImpl<User, Long> implements UserDao {

  @Autowired
  private ShardConfigDao shardConfigDao;

  public UserDaoImpl() {
    super(User.class);
  }

  protected List<ShardConfig> getAllShards() {
    List<ShardConfig> shardConfigs = shardConfigDao.getAllShards(ShardType.USER.getType());
    return shardConfigs;
  }

  public void activateUser(User user) {
    user.setRecordStatus(RecordStatus.ACTIVE.getId());
    saveOrUpdate(user);
  }

  @Override
  public List<User> getMatchingUsers(String pattern, boolean exactMatch, boolean startWith) {
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getPersistentClass());
    if (exactMatch) {
    } else if (startWith) {
      pattern = pattern + "%";
    } else {
      pattern = "%" + pattern + "%";
    }
    detachedCriteria.add(Restrictions.disjunction().add(Property.forName("firstName").eq(pattern)).add(Property.forName("lastName").eq(pattern)));
    List<User> users = new ArrayList<User>();
    List<ShardConfig> shardConfigs = getAllShards();
    for (ShardConfig config : shardConfigs) {
      users.addAll(findByCriteria(config.getId(), detachedCriteria));
    }
    return users;
  }
}
