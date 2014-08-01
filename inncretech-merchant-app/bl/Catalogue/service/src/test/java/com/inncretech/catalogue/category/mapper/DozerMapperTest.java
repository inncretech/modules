package com.inncretech.catalogue.category.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.beans.ProductCategory;
import com.inncretech.catalogue.db.beans.ProductImage;
import com.inncretech.catalogue.db.repository.ProductRepository;
import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.service.utils.mapper.CatalogueDozerMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = { "/test.xml" })
public class DozerMapperTest {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private CatalogueDozerMapper catalogueDozerMapper;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testItem() {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemTitle("itemTitle 123");
		itemDTO.setColor("color");

		List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
		itemDTOList.add(itemDTO);

		List<Item> itemsList = new ArrayList<Item>();

		mapper.map(itemDTOList, itemsList);

		Assert.assertNotNull(itemsList);
		Assert.assertEquals(1, itemsList.size());

		System.out.println("itemsList : " + itemsList);
	}

	@Test
	public void testCategory() {
		List<Integer> cateIntegerList = new ArrayList<Integer>();
		cateIntegerList.add(1);
		cateIntegerList.add(13);

		List<ProductCategory> productCategoriesList = new ArrayList<ProductCategory>();

		mapper.map(cateIntegerList, productCategoriesList);
		Assert.assertNotNull(productCategoriesList);

		for (int i = 0; i < productCategoriesList.size(); i++) {
			System.out.println("category : " + productCategoriesList.get(i));
		}
		Assert.assertEquals(2, productCategoriesList.size());
	}

	@Test
	public void testImage() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("anc.jpg");

		ProductImage productImage = new ProductImage();
		mapper.map(imageDTO, productImage);

		Assert.assertNotNull(productImage);
		Assert.assertNotNull(productImage.getImageUrl());
	}

	@Test
	public void testImageList() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("anc.jpg");

		List<ImageDTO> imageDTOList = new ArrayList<ImageDTO>();
		imageDTOList.add(imageDTO);

		List<ProductImage> productImagesList = new ArrayList<ProductImage>();
		ProductImage productImage = new ProductImage();

		mapper.map(imageDTO, productImage);

		mapper.map(imageDTOList, productImagesList);
		Assert.assertNotNull(productImage);
		Assert.assertNotNull(productImage.getImageUrl());

		Assert.assertNotNull(productImagesList);
		Assert.assertEquals(1, productImagesList.size());

		System.out.println("productImagesList : " + productImagesList);
	}

	@Transactional
	@Test
	public void testProductToDTOMap() {
//		Product product = productRepository.findOne(1l);
//		ProductDTO productDTO = new ProductDTO();
//		catalogueDozerMapper.mapBeanToDTO(product, productDTO);
//		Assert.assertNotNull(productDTO);
//		Assert.assertNotNull(productDTO.getCategoryIds());
//		Assert.assertEquals(1, productDTO.getCategoryIds().size());
//		Assert.assertNotNull(productDTO.getImageDTOs());
//		Assert.assertEquals(1, productDTO.getImageDTOs().size());
//		Assert.assertNotNull(productDTO.getImageDTOs().get(0));
//
//		Assert.assertNotNull(productDTO.getItemDTOs());
//		Assert.assertEquals(1, productDTO.getItemDTOs().size());
//		Assert.assertNotNull(productDTO.getItemDTOs().get(0));
	}

}