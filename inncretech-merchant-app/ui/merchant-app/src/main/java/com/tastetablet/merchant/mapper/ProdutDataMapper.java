package com.tastetablet.merchant.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tastetablet.catalogue.constants.Status;
import com.tastetablet.catalogue.dto.ImageDTO;
import com.tastetablet.catalogue.dto.ItemDTO;
import com.tastetablet.catalogue.dto.ProductDTO;
import com.tastetablet.merchant.services.proxy.CategoryServiceProxy;
import com.tastetablet.merchant.ui.bean.CategoryBean;
import com.tastetablet.merchant.ui.bean.ItemBean;
import com.tastetablet.merchant.ui.bean.OriginCountry;
import com.tastetablet.merchant.ui.bean.ProductBean;

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

		productDTO.setIsActive(true);
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
			// TODO
		} else {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setItemTitle(productBean.getTitle());
			itemDTO.setHeight(productBean.getDimensionsAndWeight().getHeight());
			itemDTO.setWeight(productBean.getDimensionsAndWeight().getWeight());
			itemDTO.setWidth(productBean.getDimensionsAndWeight().getWidth());
			itemDTO.setLength(productBean.getDimensionsAndWeight().getLength());
			itemDTO.setMrp(productBean.getPriceBean().getMrp());
			itemDTO.setRetailPrice(productBean.getPriceBean().getSellingPrice());
			itemDTO.setSku(productBean.getSku());
			itemDTO.setQuantity(productBean.getStock().getQuantity());
			itemDTO.setIsActive(true);
			itemDTOs.add(itemDTO);
		}
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImageUrl("test_url");
		imageDTO.setIsDefault(true);
		imageDTOs.add(imageDTO);
		productDTO.setImageDTOs(imageDTOs);
		productDTO.setItemDTOs(itemDTOs);
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
		productBean.setStatus(com.tastetablet.merchant.constants.Status.valueOf(Status.ACTIVE.toString()));
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
			populateProductDefaultDimesionandWeight(productBean, productDTO.getItemDTOs().get(0));
			for (ItemDTO itemDTO : productDTO.getItemDTOs()) {
				ItemBean itemBean = new ItemBean();
				itemBean.setTitle(itemDTO.getItemTitle());
				itemBean.getDimensionsAndWeight().setHeight(itemDTO.getHeight());
				itemBean.getDimensionsAndWeight().setWeight(itemDTO.getWeight());
				itemBean.getDimensionsAndWeight().setWidth(itemDTO.getWidth());
				itemBean.getDimensionsAndWeight().setLength(itemDTO.getLength());
				itemBean.getPriceBean().setMrp(itemDTO.getMrp());
				itemBean.getPriceBean().setSellingPrice(itemDTO.getRetailPrice());
				itemBean.setSku(itemDTO.getSku());
				itemBean.getStock().setQuantity(itemDTO.getQuantity());
				items.add(itemBean);
			}
			productBean.setItems(items);
		} else {

		}
		productBean.setMerchantId(1l);

		return productBean;
	}

	private static void populateProductDefaultDimesionandWeight(ProductBean productBean, ItemDTO itemDTO) {
		productBean.getDimensionsAndWeight().setHeight(itemDTO.getHeight());
		productBean.getDimensionsAndWeight().setWeight(itemDTO.getWeight());
		productBean.getDimensionsAndWeight().setWidth(itemDTO.getWidth());
		productBean.getDimensionsAndWeight().setLength(itemDTO.getLength());
		productBean.getPriceBean().setMrp(itemDTO.getMrp());
		productBean.getPriceBean().setSellingPrice(itemDTO.getRetailPrice());
		productBean.setSku(itemDTO.getSku());

		productBean.getStock().setQuantity(itemDTO.getQuantity());

	}

}
