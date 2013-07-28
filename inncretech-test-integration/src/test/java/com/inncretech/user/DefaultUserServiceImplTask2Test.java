package com.inncretech.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.model.AccessContext;
import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.service.UserService;

//create 1 million users
//serial execution

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplTask2Test  {

	@Autowired
	private UserService userService;
	@Autowired
	private TestUtil dbUtility;
	@Autowired
	private IdGenerator idGenerator;

	private long noOfUsers = 1000;

	Thread thread;
	AccessContext accessContext = null;

	// AccessContext() constructor is defined as private, so i can not access it
	// from the Integration test case.
	// TODO: looking for AccessContext()

	// there should be some lookup service, it should getId by name
	@Test
	public void getUser() throws Exception {
		for (int i = 0; i < noOfUsers; i++) {
			System.out.println("User Number" + i);
			//			
		}		

	}

	@Before
	public void setUp() {
		dbUtility.cleanUpdb();

	}

	
}
