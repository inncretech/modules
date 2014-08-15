package com.inncretech.catalogue.impl;

import java.math.BigDecimal;
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

import com.inncretech.catalogue.constants.Status;
import com.inncretech.catalogue.db.repository.ProductRepository;
import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.catalogue.service.ProductService;
import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class ProductServiceImplTest {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	static Integer merchantId = 1;

	static Long productId = null;

	ProductDTO createDummyProductDTO() {

		ProductDTO productDTO = new ProductDTO();

		productDTO.setDescription("description");
		productDTO.setCreateDate(new Date());
		productDTO.setEndDate(new Date());
		productDTO.setMerchantId(12l);
		productDTO.setStartDate(new Date());
		productDTO.setTitle("Title");
		productDTO.setStatus(Status.ACTIVE);

		List<Integer> categoriesList = new ArrayList<Integer>();
		categoriesList.add(1);
		productDTO.setCategoryIds(categoriesList);

		List<ImageDTO> imageDTOList = new ArrayList<ImageDTO>();
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("defaultimage.jpg");
		imageDTO.setIsDefault(true);
		imageDTOList.add(imageDTO);
		imageDTO = new ImageDTO();
		imageDTO.setImageUrl("normalimage.jpg");
		productDTO.setImageDTOs(imageDTOList);

		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemTitle("defaultTitle");
		itemDTO.setColor("default color");
		itemDTO.setQuantity(1);
		itemDTO.setLength(123.90);
		itemDTO.setMrp(new BigDecimal(12.12));
		itemDTO.setHeight(12.4);
		itemDTO.setWidth(12.45);
		itemDTO.setRetailPrice(new BigDecimal(89.90));
		itemDTO.setSku("defaultsku");
		itemDTO.setWeight(34.56);
		itemDTO.setIsActive(true);
		itemDTOList.add(itemDTO);

		itemDTO = new ItemDTO();
		itemDTO.setItemTitle("simpleTitle");
		itemDTO.setColor("simpleColor");
		itemDTO.setQuantity(2);
		itemDTO.setLength(12.90);
		itemDTO.setMrp(new BigDecimal(12.10));
		itemDTO.setHeight(12.94);
		itemDTO.setWidth(12.5);
		itemDTO.setRetailPrice(new BigDecimal(9.90));
		itemDTO.setSku("simpleSku");
		itemDTO.setWeight(34.6);
		itemDTO.setIsActive(true);

		itemDTOList.add(itemDTO);
		productDTO.setItemDTOs(itemDTOList);

		return productDTO;
	}

	ProductDTO addProductDTO() {
		List<String> errorCodes = null;
		ProductDTO productDTO = createDummyProductDTO();
		try {
			productDTO = productService.addProduct(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		} catch (InternalServiceException e) {
			errorCodes = e.getErrorCodes();
		}
		Assert.assertNull(errorCodes);

		productId = productDTO.getProductId();
		Assert.assertNotNull(productDTO.getItemDTOs());
		Assert.assertEquals(2, productDTO.getItemDTOs().size());
		for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
			Assert.assertNotNull(itemDTO);
			Assert.assertNotNull(itemDTO.getItemId());
			Assert.assertEquals(true, itemDTO.getIsActive());
		}
		return productDTO;
	}

	@Test
	@Transactional
	@Rollback(false)
	public void addProductTest() {
		ProductDTO productDTO = addProductDTO();
		Assert.assertNotNull(productDTO);
	}

	@Test
	public void getProductByProductId() throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		productRepository.findOne(1l);
		// Assert.assertNotNull(productService.getProductByProductId(1l));
	}

	@Test
	public void getAllProduct() throws InvalidArgumentException, ProductNotFoundException, InternalServiceException {
		productService.getActiveProducts(merchantId, 1, 10);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteItems() {
		List<Long> itemIds = new ArrayList<Long>();
		itemIds.add(2l);
		try {
			productService.deleteItems(itemIds);
		} catch (InvalidArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (InternalServiceException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testInavtiveProducts() {
		List<Long> productIds = new ArrayList<Long>();
		productIds.add(1l);
		try {
			productService.markProductsInActive(productIds);
		} catch (InvalidArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (InternalServiceException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testAvtiveProducts() {
		List<Long> productIds = new ArrayList<Long>();
		productIds.add(2l);
		try {
			productService.markProductsActive(productIds);
		} catch (InvalidArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (InternalServiceException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void editProduct() {

		List<String> errorCodes = null;
		ProductDTO productDTO = null;

		String description = "product description edited";
		try {
			productDTO = productService.getProductByProductId(productId);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		} catch (ProductNotFoundException e) {
			errorCodes = e.getErrorCodes();
		} catch (InternalServiceException e) {
			errorCodes = e.getErrorCodes();
		}

		productDTO.setDescription(description);

		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemTitle("edit another added");
		itemDTO.setColor("editm color");
		itemDTO.setQuantity(1);
		itemDTO.setLength(123.90);
		itemDTO.setMrp(new BigDecimal(12.12));
		itemDTO.setHeight(12.4);
		itemDTO.setWidth(12.45);
		itemDTO.setRetailPrice(new BigDecimal(89.90));
		itemDTO.setSku("edi sku");
		itemDTO.setWeight(34.56);
		itemDTO.setIsActive(true);
		itemDTOList.add(itemDTO);
		productDTO.getItemDTOs().add(itemDTO);
		// productDTO.setItemDTOs(itemDTOList);

		ImageDTO productImage = new ImageDTO();
		productImage.setImageUrl("edit-new one.jpg");
		productImage.setIsDefault(true);

		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		imageDTOs.add(productImage);

		for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
			Assert.assertNotNull(imageDTO.getImageId());
		}

		productDTO.setImageDTOs(imageDTOs);
		try {
			productService.updateProduct(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		} catch (ProductNotFoundException e) {
			errorCodes = e.getErrorCodes();
		} catch (InternalServiceException e) {
			errorCodes = e.getErrorCodes();
		}
		Assert.assertNull(errorCodes);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void editItem() {

		List<String> errorCodes = null;
		ProductDTO productDTO = null;
		try {
			productDTO = productService.getProductByProductId(productId);
		} catch (InvalidArgumentException e1) {
			e1.printStackTrace();
		} catch (ProductNotFoundException e1) {
			e1.printStackTrace();
		} catch (InternalServiceException e1) {
			e1.printStackTrace();
		}
		for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
			itemDTO.setIsActive(true);
			itemDTO.setItemTitle("This is update call.");
		}

		productDTO.setDescription("update call");
		for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
			Assert.assertEquals(true, itemDTO.getIsActive());
			Assert.assertEquals("This is update call.", itemDTO.getItemTitle());
		}

		// List<Integer> pciIntegers = new ArrayList<Integer>();
		productDTO.getCategoryIds().add(2);

		Assert.assertNotNull(productDTO.getCategoryIds());
		System.out.println("productDTO.getCategoryIds() >>> " + productDTO.getCategoryIds());
		try {
			productService.updateProduct(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		} catch (ProductNotFoundException e) {
			errorCodes = e.getErrorCodes();
		} catch (InternalServiceException e) {
			errorCodes = e.getErrorCodes();
		}
		System.out.println("errorCodes >>> " + errorCodes);
		Assert.assertNull(errorCodes);

		Assert.assertEquals(2, productDTO.getCategoryIds().size());
		Assert.assertEquals(1, productDTO.getCategoryIds().get(0).intValue());
		Assert.assertEquals(2, productDTO.getCategoryIds().get(1).intValue());
	}
}