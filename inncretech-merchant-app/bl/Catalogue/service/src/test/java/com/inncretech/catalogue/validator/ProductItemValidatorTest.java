package com.inncretech.catalogue.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.InvalidArgumentException;
import com.inncretech.catalogue.validators.ProductServiceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class ProductItemValidatorTest {

	@Autowired
	ProductServiceValidator productValidator;

	@BeforeClass
	public static void setup() {
	}

	@Before
	public void testBefore() {
		Assert.assertNotNull(productValidator);
	}

	@Test
	public void testEmptyProduct() {
		ProductDTO productDTO = new ProductDTO();
		List<String> errorCodes = null;

		try {
			productValidator.doValidate(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		}

		Assert.assertNotNull(errorCodes);
		//Assert.assertEquals(6, errorCodes.size());
	}

	@Test
	public void testNonEmptyProduct() {
		ProductDTO productDTO = new ProductDTO();
		List<String> errorCodes = null;
		productDTO.setDescription("description");
		productDTO.setCreateDate(new Date());
		productDTO.setEndDate(new Date());
		productDTO.setMerchantId(12l);
		productDTO.setStartDate(new Date());
		productDTO.setTitle("Title");
		List<Integer> categoriesList = new ArrayList<Integer>();
		categoriesList.add(1);
		productDTO.setCategoryIds(categoriesList);
		
		List<ImageDTO> imageDTOList = new ArrayList<ImageDTO>();
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("abc.jpg");
		imageDTOList.add(imageDTO);
		productDTO.setImageDTOs(imageDTOList);
		
		try {
			productValidator.doValidate(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		}
		Assert.assertNull(errorCodes);
	}
	
	@Test
	public void testItem() {
		ProductDTO productDTO = new ProductDTO();
		List<String> errorCodes = null;
		productDTO.setDescription("description");
		productDTO.setCreateDate(new Date());
		productDTO.setEndDate(new Date());
		productDTO.setMerchantId(12l);
		productDTO.setStartDate(new Date());
		productDTO.setTitle("Title");
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemTitle("itemTitle");
		
		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		itemDTOList.add(itemDTO);
		productDTO.setItemDTOs(itemDTOList);
		try {
			productValidator.doValidate(productDTO);
		} catch (InvalidArgumentException e) {
			errorCodes = e.getErrorCodes();
		}
		Assert.assertNotNull(errorCodes);
	}
	
}