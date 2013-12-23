package com.inncretech.follow;

import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.BaseTest;
import com.inncretech.core.sharding.IdGenerator;

public abstract class AbstractDaoTest extends BaseTest {

  @Autowired
  private IdGenerator idGenerator;

  public IdGenerator getIdGenerator() {
    return idGenerator;
  }
}
