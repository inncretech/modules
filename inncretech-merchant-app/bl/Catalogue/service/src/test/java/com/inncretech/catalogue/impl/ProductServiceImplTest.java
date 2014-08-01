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
import com.inncretech.catalogue.exceptions.InternalServiceException;
import com.inncretech.catalogue.exceptions.InvalidArgumentException;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.catalogue.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class ProductServiceImplTest {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	public void addProductTest() {

		ProductDTO productDTO = new ProductDTO();
		List<String> errorCodes = null;
		productDTO.setDescription("description");
		productDTO.setCreateDate(new Date());
		productDTO.setEndDate(new Date());
		productDTO.setMerchantId(12l);
		productDTO.setStartDate(new Date());
		productDTO.setTitle("Title");
		productDTO.setIsActive(true);
		productDTO.setStatus(Status.ACTIVE);

		List<Integer> categoriesList = new ArrayList<Integer>();
		categoriesList.add(1);
		productDTO.setCategoryIds(categoriesList);

		List<ImageDTO> imageDTOList = new ArrayList<ImageDTO>();
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("abc.jpg");
		imageDTOList.add(imageDTO);
		productDTO.setImageDTOs(imageDTOList);

		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemTitle("itemTitle 123");
		itemDTO.setColor("color");
		itemDTO.setQuantity(1);
		itemDTO.setLength(123.90);
		itemDTO.setMrp(new BigDecimal(12.12));
		itemDTO.setHeight(12.4);
		itemDTO.setWidth(12.45);
		itemDTO.setRetailPrice(new BigDecimal(89.90));
		itemDTO.setSku("sku123");
		itemDTO.setWeight(34.56);
		itemDTO.setIsActive(true);
		itemDTOList.add(itemDTO);

		productDTO.setItemDTOs(itemDTOList);

		System.out.println("productDTO : " + productDTO);
		try {
			productDTO = productService.addProduct(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		} catch (InternalServiceException e) {
			errorCodes = e.getErrorCodes();
		}

		Assert.assertNull(errorCodes);

		// Assert.assertNotNull(productDTO);
		// Assert.assertNotNull(productDTO.getProductId());

	}

	@Test
	public void getProductByProductId() throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		productRepository.findOne(1l);
		//Assert.assertNotNull(productService.getProductByProductId(1l));
	}

	@Test
	public void getAllProduct() throws InvalidArgumentException, ProductNotFoundException, InternalServiceException {
		productService.getActiveProducts(1, 10);
	}
}