package com.inncretech.merchant.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.catalogue.constants.Status;
import com.inncretech.catalogue.dto.ImageDTO;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.merchant.services.proxy.CategoryServiceProxy;
import com.inncretech.merchant.ui.bean.CategoryBean;
import com.inncretech.merchant.ui.bean.ImageBean;
import com.inncretech.merchant.ui.bean.ItemBean;
import com.inncretech.merchant.ui.bean.OriginCountry;
import com.inncretech.merchant.ui.bean.ProductBean;

@Component
public class ProdutDataMapper {

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	private static final Map<Integer, String> COUNTRY_MAP = new LinkedHashMap<Integer, String>();

	@PostConstruct
	public void init() {
		COUNTRY_MAP.put(1, "India");
		COUNTRY_MAP.put(2, "USA");
	}

	public ProductDTO mapUIBeanToServiceBean(ProductBean productBean) {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setProductId(productBean.getProductId());
		productDTO.setDescription(productBean.getDescription());
		productDTO.setTitle(productBean.getTitle());

		productDTO.setStartDate(productBean.getStartDate());
		productDTO.setEndDate(productBean.getEndDate());
		productDTO.setStatus(Status.ACTIVE);
		productDTO.setCreateDate(new Date());
		productDTO.setOriginCountry(productBean.getOriginCountry().getCountryId());

		List<Integer> categoryIds = new ArrayList<Integer>();
		if (productBean.getCategoryIds() != null && !productBean.getCategoryIds().isEmpty()) {
			for (Integer categoryId : productBean.getCategoryIds()) {
				categoryIds.add(categoryId);
			}
		}
		productDTO.setCategoryIds(categoryIds);

		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		if (productBean.getItems() != null && !productBean.getItems().isEmpty()) {
			for (ItemBean itemBean : productBean.getItems()) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setItemId(itemBean.getItemId());
				itemDTO.setItemTitle(itemBean.getTitle());
				itemDTO.setHeight(itemBean.getDimensionsAndWeight().getHeight());
				itemDTO.setWeight(itemBean.getDimensionsAndWeight().getWeight());
				itemDTO.setWidth(itemBean.getDimensionsAndWeight().getWidth());
				itemDTO.setLength(itemBean.getDimensionsAndWeight().getLength());
				itemDTO.setMrp(itemBean.getPriceBean().getMrp());
				itemDTO.setRetailPrice(itemBean.getPriceBean().getSellingPrice());
				itemDTO.setSku(itemBean.getSku());
				itemDTO.setQuantity(itemBean.getStock().getQuantity());
				itemDTO.setIsActive(itemBean.getIsActive() != null ? itemBean.getIsActive() : true);
				itemDTOs.add(itemDTO);
			}
			productDTO.setItemDTOs(itemDTOs);
		}
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();

		if (productBean.getImages() != null && !productBean.getImages().isEmpty()) {
			for (ImageBean imageBean : productBean.getImages()) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImageUrl(imageBean.getImageUrl());
				imageDTO.setIsDefault(true);
				imageDTOs.add(imageDTO);
			}
			productDTO.setImageDTOs(imageDTOs);
		}

		productDTO.setMerchantId(1l);

		return productDTO;
	}

	public ProductBean mapServiceBeanToUIBean(ProductDTO productDTO) {
		ProductBean productBean = new ProductBean();
		productBean.setProductId(productDTO.getProductId());
		productBean.setDescription(productDTO.getDescription());
		productBean.setTitle(productDTO.getTitle());

		productBean.setStartDate(productDTO.getStartDate());
		productBean.setEndDate(productDTO.getEndDate());

		productBean.setIsActive(true);
		productBean.setStatus(com.inncretech.merchant.constants.Status.valueOf(Status.ACTIVE.toString()));
		productBean.setCreateDate(new Date());

		if (productDTO.getCategoryIds() != null && !productDTO.getCategoryIds().isEmpty()) {
			List<Integer> categoryIds = new ArrayList<Integer>();
			Map<Integer, List<CategoryBean>> categoryIdToCategoryBeanList = new LinkedHashMap<Integer, List<CategoryBean>>();
			for (Integer categoryId : productDTO.getCategoryIds()) {
				categoryIds.add(categoryId);
				categoryIdToCategoryBeanList
						.put(categoryId, categoryServiceProxy.getBreadCrumbByCategoryId(categoryId));
			}
			productBean.setCategoryIds(categoryIds);
			productBean.setCategoryIdToCategoryBeanList(categoryIdToCategoryBeanList);
		}
		productBean.setProductId(productDTO.getProductId());
		productBean.setCreateDate(productDTO.getCreateDate());
		OriginCountry originCountry = new OriginCountry();
		originCountry.setCountryId(productDTO.getOriginCountry());
		originCountry.setCountryName(COUNTRY_MAP.get(productDTO.getOriginCountry()));
		productBean.setOriginCountry(originCountry);

		if (productDTO.getItemDTOs() != null && !productDTO.getItemDTOs().isEmpty()) {
			List<ItemBean> items = new ArrayList<ItemBean>();
			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				ItemBean itemBean = new ItemBean();
				itemBean.setItemId(itemDTO.getItemId());
				itemBean.setTitle(itemDTO.getItemTitle());
				itemBean.getDimensionsAndWeight().setHeight(itemDTO.getHeight());
				itemBean.getDimensionsAndWeight().setWeight(itemDTO.getWeight());
				itemBean.getDimensionsAndWeight().setWidth(itemDTO.getWidth());
				itemBean.getDimensionsAndWeight().setLength(itemDTO.getLength());
				itemBean.getPriceBean().setMrp(itemDTO.getMrp());
				itemBean.getPriceBean().setSellingPrice(itemDTO.getRetailPrice());
				itemBean.setSku(itemDTO.getSku());
				itemBean.getStock().setQuantity(itemDTO.getQuantity());
				itemBean.setIsActive(itemDTO.getIsActive());
				items.add(itemBean);
			}
			productBean.setItems(items);
		}
		productBean.setMerchantId(productDTO.getMerchantId());

		List<ImageBean> imageBeans = new ArrayList<ImageBean>();

		if (productDTO.getImageDTOs() != null && !productDTO.getImageDTOs().isEmpty()) {
			for (ImageDTO imageDTO : productDTO.getImageDTOs()) {
				ImageBean imageBean = new ImageBean();
				imageBean.setImageUrl(imageDTO.getImageUrl());
				imageBeans.add(imageBean);
			}
			productBean.setImages(imageBeans);
		}

		return productBean;
	}

}
