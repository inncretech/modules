package com.inncretech.catalogue.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.db.beans.Category;
import com.inncretech.catalogue.db.beans.Product;
import com.inncretech.catalogue.db.beans.ProductCategory;
import com.inncretech.catalogue.db.repository.CategoryRepository;
import com.inncretech.catalogue.db.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class ProductCategoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void testEqualsAndHashCode() {

		Set<ProductCategory> productCategories = new HashSet<ProductCategory>();
		Product product = productRepository.findOne(1l);
		Category category = categoryRepository.findOne(1);
		ProductCategory productCategory = new ProductCategory();

		productCategory.setProduct(product);
		productCategory.setCategory(category);
		productCategory.setIsActive(true);
		productCategories.add(productCategory);

		productCategory = new ProductCategory();
		productCategory.setProduct(product);
		productCategory.setCategory(category);
		productCategory.setIsActive(false);
		productCategories.add(productCategory);

		org.junit.Assert.assertEquals(1, productCategories.size());

	}
}