package com.inncretech.core;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.core.test.TestUtil;

public class BaseTest {
  
  @Autowired
  private TestUtil testUtility;
  
  @Before
  public void setUp() {
   // testUtility.cleanUpdb();
  }

}
