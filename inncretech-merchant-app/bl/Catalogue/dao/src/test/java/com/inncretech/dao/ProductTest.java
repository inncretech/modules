package com.inncretech.dao;

import java.util.ArrayList;
import java.util.Date;
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

import com.inncretech.catalogue.db.beans.Category;
import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.beans.Product;
import com.inncretech.catalogue.db.beans.ProductCategory;
import com.inncretech.catalogue.db.beans.ProductImage;
import com.inncretech.catalogue.db.enums.Status;
import com.inncretech.catalogue.db.repository.CategoryRepository;
import com.inncretech.catalogue.db.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/dao.xml" })
@ActiveProfiles(profiles = "dev")
public class ProductTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	@Test
	public void testGetProductByItemIds() {
		List<Long> items = new ArrayList<Long>();
		items.add(1l);
		List<Product> products = productRepository.findProductByItemsItemIds(items);
		Assert.assertNotNull(products);
		for (Product product : products) {
			System.out.println(product.getTitle());
		}
	}

	@Transactional
	@Test
	public void testGetProduct() {
		// Product product = productRepository.getOne(90L);
		// Assert.assertNull(product);
	}

	@Transactional
	@Rollback(false)
	@Test
	public void testToSaveProduct() {
		Product product = new Product();
		product.setDescription("Test id Description");
		product.setEndDate(new Date());
		product.setMerchantId(1l);
		product.setOriginCountry(1);
		product.setStartDate(new Date());
		product.setStatus(Status.ACTIVE);
		product.setTitle("Test Title");

		List<ProductCategory> categoriesProductCategories = new ArrayList<ProductCategory>();

		ProductCategory productCategory = new ProductCategory();

		productCategory.setProduct(product);
		Category category = categoryRepository.findOne(1);
		productCategory.setCategory(category);

		categoriesProductCategories.add(productCategory);

		product.setProductCategories(categoriesProductCategories);

		List<ProductImage> productImagesList = new ArrayList<ProductImage>();

		ProductImage defaultProductImage = new ProductImage();
		defaultProductImage.setImageUrl("abc.jpg");
		defaultProductImage.setIsDefault(true);

		ProductImage productImage = new ProductImage();
		productImage.setImageUrl("efg.jpg");
		productImage.setIsDefault(false);
		productImage.setProduct(product);
		productImagesList.add(defaultProductImage);
		productImagesList.add(productImage);
		product.setProductImages(productImagesList);

		Item item = new Item();
		item.setColor("red");
		item.setIsActive(true);
		item.setProduct(product);
		List<Item> itemsList = new ArrayList<Item>();
		itemsList.add(item);

		product.setItems(itemsList);

		productRepository.save(product);

		Assert.assertNotNull(product);
		Assert.assertNotNull(product.getProductId());

		Assert.assertNotNull(product.getProductImages());
		for (ProductImage image : product.getProductImages()) {
			Assert.assertNotNull(image.getImageId());
		}
	}

}
