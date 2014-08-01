package com.inncretech.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.db.repository.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/dao.xml" })
@ActiveProfiles(profiles = "dev")
public class ItemTest {

	@Autowired
	ItemRepository itemRepository;
	
	@Test
	public void testItem(){
		List<Long> longs = new ArrayList<Long>();
		longs.add(1l);
		Assert.assertNotNull(itemRepository.findByItemIds(longs));
	}

}
