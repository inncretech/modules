package com.inncretech.catalogue.service.utils.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.catalogue.db.beans.Category;
import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.beans.Product;
import com.inncretech.catalogue.db.beans.ProductCategory;
import com.inncretech.catalogue.db.beans.ProductImage;
import com.inncretech.catalogue.db.enums.Status;
import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;

public class CatalogueDozerMapper {

	@Autowired
	private DozerBeanMapper mapper;

	public CatalogueDozerMapper() {
	}

	void ProductDTOToProduct(ProductDTO productDTO, Product product) {
		if (productDTO != null) {
			product.setCreateDate(productDTO.getCreateDate());
			product.setDescription(productDTO.getDescription());
			product.setEndDate(productDTO.getEndDate());
			product.setMerchantId(productDTO.getMerchantId());
			product.setOriginCountry(productDTO.getOriginCountry());
			product.setProductId(productDTO.getProductId());
			product.setStartDate(productDTO.getStartDate());
			product.setStatus(Status.valueOf(productDTO.getStatus().toString()));
			product.setTitle(productDTO.getTitle());
			product.setProductId(productDTO.getProductId());
		}
	}

	void mapProductCatagoriesToProduct(ProductDTO productDTO, Product product) {
		List<ProductCategory> productCategoriesList = new ArrayList<ProductCategory>();
		for (Integer categoryId : productDTO.getCategoryIds()) {
			ProductCategory productCategory = new ProductCategory();
			Category category = new Category();
			category.setCategoryId(categoryId);
			productCategory.setCategory(category);
			productCategory.setProduct(product);
			productCategory.setIsActive(true);
			productCategoriesList.add(productCategory);
		}
		product.setProductCategories(productCategoriesList);
	}

	void mapImageDTOToProduct(ProductDTO productDTO, Product product) {
		List<ProductImage> productImages = new ArrayList<ProductImage>();
		if (productDTO.getImageDTOs() != null && !productDTO.getImageDTOs().isEmpty()) {
			for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
				ProductImage productImage = new ProductImage();
				productImage.setImageId(imageDTO.getImageId());
				productImage.setIsDefault(imageDTO.getIsDefault());
				productImage.setImageUrl(imageDTO.getImageUrl());
				productImage.setProduct(product);
				productImages.add(productImage);
			}
		}
		product.setProductImages(productImages);
	}

	public Item convertItemDTOIntoItem(ItemDTO itemDTO) {
		Item item = new Item();
		item.setItemId(itemDTO.getItemId());
		item.setColor(itemDTO.getColor());
		item.setHeight(itemDTO.getHeight());
		item.setTitle(itemDTO.getItemTitle());
		item.setLength(itemDTO.getLength());
		item.setMrp(itemDTO.getMrp());
		item.setQuantity(itemDTO.getQuantity());
		item.setOfferPrice(itemDTO.getRetailPrice());
		item.setSku(itemDTO.getSku());
		item.setWeight(itemDTO.getWeight());
		item.setWidth(itemDTO.getWidth());
		item.setIsActive(itemDTO.getIsActive());
		return item;
	}

	void mapItemDTOToProduct(ProductDTO productDTO, Product product) {
		List<Item> items = new ArrayList<Item>();
		if (productDTO.getItemDTOs() != null && !productDTO.getItemDTOs().isEmpty()) {
			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				Item item = convertItemDTOIntoItem(itemDTO);
				item.setProduct(product);
				items.add(item);
			}
		}
		product.setItems(items);
	}

	void mapproductDTOToProduct(Product product, ProductDTO productDTO) {
		if (product != null) {
			productDTO.setCreateDate(product.getCreateDate());
			productDTO.setDescription(product.getDescription());
			productDTO.setEndDate(product.getEndDate());
			productDTO.setMerchantId(product.getMerchantId());
			productDTO.setOriginCountry(product.getOriginCountry());
			productDTO.setProductId(product.getProductId());
			productDTO.setStartDate(product.getStartDate());
			productDTO.setStatus(com.inncretech.catalogue.constants.Status.valueOf(product.getStatus().toString()));
			productDTO.setTitle(product.getTitle());
		}
	}

	public ItemDTO convertItemIntoItemDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemId(item.getItemId());
		itemDTO.setColor(item.getColor());
		itemDTO.setHeight(item.getHeight());
		itemDTO.setItemTitle(item.getTitle());
		itemDTO.setLength(item.getLength());
		itemDTO.setMrp(item.getMrp());
		itemDTO.setQuantity(item.getQuantity());
		itemDTO.setRetailPrice(item.getOfferPrice());
		itemDTO.setSku(item.getSku());
		itemDTO.setWeight(item.getWeight());
		itemDTO.setIsActive(item.getIsActive());
		itemDTO.setWidth(item.getWidth());
		return itemDTO;
	}

	List<ItemDTO> convertItemToItemDTO(Product product) {
		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		if (product.getItems() != null && !product.getItems().isEmpty()) {
			for (Item item : product.getItems()) {
				itemDTOs.add(convertItemIntoItemDTO(item));
			}
		}
		return itemDTOs;
	}

	List<ImageDTO> convertProductImageToImageDTO(Product product) {
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
			for (ProductImage productImage : product.getProductImages()) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImageId(productImage.getImageId());
				imageDTO.setIsDefault(productImage.getIsDefault());
				imageDTO.setImageUrl(productImage.getImageUrl());
				imageDTOs.add(imageDTO);
			}
		}
		return imageDTOs;
	}

	List<Integer> convertProductCategoryIdIntoProductDTO(Product product) {
		List<Integer> categoryIds = new ArrayList<Integer>();
		if (product != null && product.getProductCategories() != null && !product.getProductCategories().isEmpty()) {
			for (ProductCategory productCategory : product.getProductCategories()) {
				categoryIds.add(productCategory.getCategory().getCategoryId());
			}
		}
		return categoryIds;
	}

	public void mapProductToProductDTO(Product product, ProductDTO productDTO) {
		mapproductDTOToProduct(product, productDTO);
		productDTO.setCategoryIds(convertProductCategoryIdIntoProductDTO(product));
		productDTO.setItemDTOs(convertItemToItemDTO(product));
		productDTO.setImageDTOs(convertProductImageToImageDTO(product));
	}

	public void mapProductDTOToProduct(ProductDTO productDTO, Product product) {
		ProductDTOToProduct(productDTO, product);
		mapProductCatagoriesToProduct(productDTO, product);
		mapImageDTOToProduct(productDTO, product);
		mapItemDTOToProduct(productDTO, product);
	}

	public void createProductDTOsFromProducts(List<Product> products) {
		List<ProductDTO> productDTOs = null;
		if (products != null && !products.isEmpty()) {
			productDTOs = new ArrayList<ProductDTO>(products.size());
			for (Product product : products) {
				ProductDTO productDTO = new ProductDTO();
				mapProductToProductDTO(product, productDTO);
				productDTOs.add(productDTO);
			}
		}
	}

}