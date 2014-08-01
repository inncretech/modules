package com.inncretech.catalogue.category.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class CategoryTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void getCategoryService() {
		System.out.println(categoryService.getCategoryNodeByCategoryId(1));
	}
}
