package com.inncretech.identity.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;
import com.inncretech.identity.dto.UserDTO;
import com.inncretech.identity.exceptions.UnknownUserException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/identityService.xml" })
@ActiveProfiles(profiles = "dev")
public class IdentityServiceImplTest {

	@Autowired
	private IdentityService identityService;

	@Test
	public void testGetUserByUserId() {
		UserDTO userDTO = null;
		try {
			userDTO = identityService.getUserByUserId(21l);
		} catch (InvalidArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (UnknownUserException e) {
			Assert.fail(e.getMessage());
		} catch (InternalServiceException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(userDTO);
		Assert.assertNotNull(userDTO.getRoles());
	}

	@Test
	public void testGetUserByUserName() {
		UserDTO userDTO = null;
		try {
			userDTO = identityService.getUserByUserName("test");
		} catch (InvalidArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (UnknownUserException e) {
			Assert.fail(e.getMessage());
		} catch (InternalServiceException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(userDTO);
		Assert.assertNotNull(userDTO.getRoles());
	}

}