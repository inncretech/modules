package com.inncretech.catalogue.service.utils.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	void resetProductCategoryInProductIfPresent(Product product) {
		if (product.getProductCategories() != null && !product.getProductCategories().isEmpty()) {
			for (ProductCategory productCategory : product.getProductCategories()) {
				productCategory.setIsActive(false);
			}
		} else {
			product.setProductCategories(new HashSet<ProductCategory>());
		}
	}

	void mapProductCatagoriesToProduct(ProductDTO productDTO, Product product) {
		resetProductCategoryInProductIfPresent(product);
		if (productDTO.getCategoryIds() != null) {
			for (Integer categoryId : productDTO.getCategoryIds()) {
				ProductCategory productCategory = new ProductCategory();
				Category category = new Category();
				category.setCategoryId(categoryId);
				productCategory.setCategory(category);
				productCategory.setIsActive(true);
				productCategory.setProduct(product);
				if (product.getProductCategories().contains(productCategory)) {
					product.getProductCategories().remove(productCategory);
				}
				product.getProductCategories().add(productCategory);
			}
		}
	}

	ProductImage getProductImage(Product product, Long imageId) {
		if (product.getProductImages() != null) {
			for (ProductImage productImage : product.getProductImages()) {
				if (productImage.getImageId() == imageId) {
					return productImage;
				}
			}
		}
		return new ProductImage();
	}

	void mapImageDTOToProduct(ProductDTO productDTO, Product product) {

		if (product.getProductImages() == null) {
			product.setProductImages(new HashSet<ProductImage>());
		}

		Set<ProductImage> removalProductImages = new HashSet<ProductImage>();
		if (product.getProductImages() != null) {
			for (ProductImage productImage : product.getProductImages()) {
				boolean imageFound = false;
				for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
					if (imageDTO.getImageId() == productImage.getImageId()) {
						imageFound = true;
					}
				}
				if (!imageFound) {
					removalProductImages.add(productImage);
				}
			}
		}
		if (removalProductImages != null && !removalProductImages.isEmpty()) {
			for (ProductImage productImage : removalProductImages) {
				productImageRepository.delete(productImage.getImageId());
				product.getProductImages().remove(productImage);
			}
		}

		if (productDTO.getImageDTOs() != null && !productDTO.getImageDTOs().isEmpty()) {
			for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
				ProductImage productImage = getProductImage(product, imageDTO.getImageId());
				productImage.setImageId(imageDTO.getImageId());
				productImage.setIsDefault(imageDTO.getIsDefault());
				productImage.setImageUrl(imageDTO.getImageUrl());
				productImage.setProduct(product);
				product.getProductImages().add(productImage);
			}
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

	void resetItemsInProductIfPresent(Product product) {
		if (product.getItems() != null && !product.getItems().isEmpty()) {
			for (Item item : product.getItems()) {
				item.setIsActive(false);
			}
		} else {
			product.setItems(new HashSet<Item>());
		}
	}

	void mapItemDTOToProduct(ProductDTO productDTO, Product product) {
		resetItemsInProductIfPresent(product);
		if (productDTO.getItemDTOs() != null && !productDTO.getItemDTOs().isEmpty()) {
			// put items in hashmap to check uniqueness.
			Map<Long, Item> itemsMap = new HashMap<Long, Item>();
			if (product.getItems() == null) {
				product.setItems(new HashSet<Item>());
			} else {
				for (Item item : product.getItems()) {
					itemsMap.put(item.getItemId(), item);
				}
			}
			// if item is present in map then do changes to that else create new.
			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				Item item = itemsMap.get(itemDTO.getItemId());
				if (item == null) {
					item = new Item();
				}
				convertItemDTOIntoItem(itemDTO, item);
				item.setProduct(product);
				product.getItems().add(item);
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