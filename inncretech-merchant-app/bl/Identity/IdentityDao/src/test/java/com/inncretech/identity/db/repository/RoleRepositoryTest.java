package com.inncretech.identity.db.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.identity.db.beans.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/identityDao.xml" })
@ActiveProfiles(profiles = "dev")
public class RoleRepositoryTest {

	@Autowired
	RoleRepository roleRepository;

	@Transactional
	@Test
	public void addRoleTest() {
		Role role = new Role();
		role.setRoleName("admin");
		role.setRoleDescription("Admin role changed");

		roleRepository.save(role);
	}

}
