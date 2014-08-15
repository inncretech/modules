package com.inncretech.catalogue.service.utils.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.catalogue.db.beans.Category;
import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.beans.Product;
import com.inncretech.catalogue.db.beans.ProductCategory;
import com.inncretech.catalogue.db.beans.ProductImage;
import com.inncretech.catalogue.db.enums.Status;
import com.inncretech.catalogue.db.repository.ProductImageRepository;
import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;

public class CatalogueDozerMapper {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private ProductImageRepository productImageRepository;

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

		if (product.getProductCategories() == null) {
			product.setProductCategories(new HashSet<ProductCategory>());
		}

		Set<Integer> categoryDBIds = new HashSet<Integer>();
		Set<Integer> categotyDTOIds = new HashSet<Integer>();

		for (Integer categoryId : productDTO.getCategoryIds()) {
			categotyDTOIds.add(categoryId.intValue());
		}

		for (ProductCategory productCategory : product.getProductCategories()) {
			if (!categotyDTOIds.contains(productCategory.getCategory().getCategoryId().intValue())) {
				productCategory.setIsActive(false);
			}
			categoryDBIds.add(productCategory.getCategory().getCategoryId().intValue());
		}

		for (Integer categoryId : categotyDTOIds) {
			if (!categoryDBIds.contains(categoryId.intValue())) {
				ProductCategory productCategory = new ProductCategory();
				Category category = new Category();
				category.setCategoryId(categoryId);
				productCategory.setCategory(category);
				productCategory.setIsActive(true);
				productCategory.setProduct(product);
				product.getProductCategories().add(productCategory);
			}
		}
	}

	ProductImage getProductImage(Product product, Long imageId) {
		if (product.getProductImages() != null) {
			for (ProductImage productImage : product.getProductImages()) {
				if (productImage.getImageId().longValue() == imageId.longValue()) {
					return productImage;
				}
			}
		}
		throw new IllegalArgumentException("imageId : " + imageId + " is not avail.");
	}

	void mapImageDTOToProduct(ProductDTO productDTO, Product product) {

		if (product.getProductImages() == null) {
			product.setProductImages(new HashSet<ProductImage>());
		}

		Set<Long> imageDTOIds = new HashSet<Long>();
		Set<Long> imageDBIds = new HashSet<Long>();
		Set<ProductImage> newProductImagesSet = new HashSet<ProductImage>();
		Set<ProductImage> removeProductImageSet = new HashSet<ProductImage>();

		for (ProductImage productImage : product.getProductImages()) {
			imageDBIds.add(productImage.getImageId().longValue());
		}

		if (productDTO.getImageDTOs() != null && !productDTO.getImageDTOs().isEmpty()) {
			for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
				ProductImage productImage = null;
				if (imageDTO.getImageId() != null && imageDBIds.contains(imageDTO.getImageId().longValue())) {
					imageDTOIds.add(imageDTO.getImageId().longValue());
					productImage = getProductImage(product, imageDTO.getImageId());
				} else {
					productImage = new ProductImage();
					newProductImagesSet.add(productImage);
				}
				productImage.setIsDefault(imageDTO.getIsDefault());
				productImage.setImageUrl(imageDTO.getImageUrl());
				productImage.setProduct(product);
			}
		}

		for (ProductImage productImage : product.getProductImages()) {
			if (!imageDTOIds.contains(productImage.getImageId().longValue())) {
				removeProductImageSet.add(productImage);
				productImageRepository.delete(productImage.getImageId().longValue());
			}
		}
		if (!removeProductImageSet.isEmpty()) {
			product.getProductImages().removeAll(removeProductImageSet);
		}
		if (!newProductImagesSet.isEmpty()) {
			product.getProductImages().addAll(newProductImagesSet);
		}
	}

	public void convertItemDTOIntoItem(ItemDTO itemDTO, Item item) {
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
	}

	void mapItemDTOToProduct(ProductDTO productDTO, Product product) {

		if (product.getItems() == null) {
			product.setItems(new HashSet<Item>());
		}

		if (productDTO.getItemDTOs() != null && !productDTO.getItemDTOs().isEmpty()) {
			Set<Long> itemDtoIdSet = new HashSet<Long>();
			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				if (itemDTO.getItemId() != null) {
					itemDtoIdSet.add(itemDTO.getItemId().longValue());
				}
			}

			if (product.getItems() != null && !product.getItems().isEmpty()) {
				for (Item item : product.getItems()) {
					if (!itemDtoIdSet.contains(item.getItemId().longValue())) {
						item.setIsActive(false);
					}
				}
			}

			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				Item itemFound = null;
				for (Item item : product.getItems()) {
					if (itemDTO.getItemId() != null && item.getItemId() != null
							&& itemDTO.getItemId().longValue() == item.getItemId().longValue()) {
						itemFound = item;
						break;
					}
				}
				if (itemFound == null) {
					itemFound = new Item();
					product.getItems().add(itemFound);
				}
				convertItemDTOIntoItem(itemDTO, itemFound);
				itemFound.setProduct(product);
			}
		}
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
				if (productCategory.getIsActive()) {
					categoryIds.add(productCategory.getCategory().getCategoryId());
				}
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