package com.inncretech.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.repository.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/dao.xml" })
@ActiveProfiles(profiles = "dev")
public class ItemTest {

	@Autowired
	ItemRepository itemRepository;

	@Test
	public void testItem() {
		// List<Long> longs = new ArrayList<Long>();
		// longs.add(1l);
		// Assert.assertNotNull(itemRepository.findByItemIds(longs));
		Item item = new Item();
		// item.setItemId(22l);
		// item.setTitle("Item Title testing");
		// itemRepository.save(item);

		Set<Item> items = new HashSet<Item>();
		item.setItemId(22l);
		item.setColor("red");
		items.add(item);

		item = new Item();
		item.setItemId(22l);
		item.setColor("black");
		org.junit.Assert.assertEquals(true, items.add(item));

		org.junit.Assert.assertEquals(1, items.size());
		for (Item item2 : items) {
			org.junit.Assert.assertEquals("black", item2.getColor());
		}
	}
}
