package com.inncretech.identity.db.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.identity.db.beans.Role;
import com.inncretech.identity.db.beans.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/identityDao.xml" })
@ActiveProfiles(profiles = "dev")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	@Rollback(false)
	@Test
	public void testAddUser() {
		Role role = roleRepository.getOne("admin123");
		Assert.assertNotNull(role);
		User user = new User();
		user.setUserName("transient12");
		user.setPassword("password");
		user.setEmail("test@gmail.com");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setIsActive(true);

		List<Role> roles = new ArrayList<Role>();
		user.setRoles(roles);
		roles.add(role);
		userRepository.save(user);
	}

	@Test
	public void testGetUser() {
		User user = userRepository.findOne(21l);
		Assert.assertNotNull(user);
	}
}