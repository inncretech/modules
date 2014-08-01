package com.tastetablet.merchant.ui.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.tastetablet.catalogue.dto.ProductDTO;
import com.tastetablet.catalogue.exceptions.InternalServiceException;
import com.tastetablet.catalogue.exceptions.InvalidArgumentException;
import com.tastetablet.catalogue.exceptions.ProductNotFoundException;
import com.tastetablet.merchant.constants.UrlConstants;
import com.tastetablet.merchant.mapper.ProdutDataMapper;
import com.tastetablet.merchant.services.proxy.CatalogueServiceProxy;
import com.tastetablet.merchant.services.proxy.CategoryServiceProxy;
import com.tastetablet.merchant.ui.bean.DropDownBean;
import com.tastetablet.merchant.ui.bean.ProductBean;

@Controller
public class ProductController {

	@Autowired
	private CatalogueServiceProxy catalogueServiceProxy;

	@Autowired
	private CategoryServiceProxy categoryServiceProxy;

	@Autowired
	private ProdutDataMapper produtDataMapper;

	// TODO need to create this from DB
	private static final Map<Integer, String> COUNTRY_MAP = new LinkedHashMap<Integer, String>();

	@PostConstruct
	public void init() {
		COUNTRY_MAP.put(1, "India");
		COUNTRY_MAP.put(2, "USA");
	}

	@RequestMapping(value = UrlConstants.EDIt_PRODUCT_PAGE, method = { RequestMethod.GET })
	public String editProduct(Model model, final @PathVariable("productId") Long productId) {
		try {
			ProductDTO productDTO = catalogueServiceProxy.getProductByProductId(productId);
			ProductBean productBean = produtDataMapper.mapServiceBeanToUIBean(productDTO);
			model.addAttribute("productBean", productBean);

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		} catch (InternalServiceException e) {
			e.printStackTrace();
		}
		populateDropDownBean(model);
		return UrlConstants.ADD_PRODUCT_PAGE;
	}

	@RequestMapping(value = UrlConstants.ADD_PRODUCT_PAGE, method = { RequestMethod.GET })
	public String addProduct(Model model) {
		model.addAttribute("productBean", new ProductBean());
		populateDropDownBean(model);
		return null;
	}

	private void populateDropDownBean(Model model) {
		DropDownBean dropDownBean = new DropDownBean();

		dropDownBean.setCountryMap(COUNTRY_MAP);
		model.addAttribute("dropDownBean", dropDownBean);
	}

	@RequestMapping(value = UrlConstants.EDIt_PRODUCT_PAGE, method = { RequestMethod.POST })
	public String editProduct(@Valid @ModelAttribute("productBean") ProductBean productBean,
			final BindingResult bindingResult, Model model, final @PathVariable("productId") Long productId) {
		String redirectURL = null;
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		ProductDTO productDTO = null;

		if (bindingResult.hasErrors()) {
			requestAttributes.setAttribute("errors", "add.product.field.error", RequestAttributes.SCOPE_REQUEST);
		} else {

			try {
				productDTO = catalogueServiceProxy.addProduct(produtDataMapper.mapUIBeanToServiceBean(productBean));
				productBean = produtDataMapper.mapServiceBeanToUIBean(productDTO);
				model.addAttribute("productBean", productBean);
				requestAttributes.setAttribute("success", "product.updated.sucessfully",
						RequestAttributes.SCOPE_REQUEST);
				redirectURL = UrlConstants.PRODUCT_SUCCESS_PAGE;
			} catch (InvalidArgumentException e) {
				String message = "";
				for (String errorCodes : e.getErrorCodes()) {
					message = message + errorCodes + "<br/>";
				}
				requestAttributes.setAttribute("errors", message, RequestAttributes.SCOPE_REQUEST);
			} catch (InternalServiceException e) {
				requestAttributes.setAttribute("errors", e.getErrorCodes(), RequestAttributes.SCOPE_REQUEST);
			}
		}
		populateDropDownBean(model);
		return redirectURL;
	}

	@RequestMapping(value = UrlConstants.ADD_PRODUCT_PAGE, method = { RequestMethod.POST })
	@Transactional
	public String addProduct(@Valid @ModelAttribute("productBean") ProductBean productBean,
			final BindingResult bindingResult, Model model) {
		String redirectURL = null;
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

		if (bindingResult.hasErrors()) {
			requestAttributes.setAttribute("errors", "add.product.field.error", RequestAttributes.SCOPE_REQUEST);
		} else {

			try {
				ProductDTO productDTOTemp = catalogueServiceProxy.addProduct(produtDataMapper
						.mapUIBeanToServiceBean(productBean));
				productBean = produtDataMapper.mapServiceBeanToUIBean(productDTOTemp);
				model.addAttribute("productBean", productBean);
				requestAttributes.setAttribute("success", "product.added.sucessfully", RequestAttributes.SCOPE_REQUEST);
				redirectURL = UrlConstants.PRODUCT_SUCCESS_PAGE;
			} catch (InvalidArgumentException e) {
				String message = "";
				for (String errorCodes : e.getErrorCodes()) {
					message = message + errorCodes + "<br/>";
				}
				requestAttributes.setAttribute("errors", message, RequestAttributes.SCOPE_REQUEST);
			} catch (InternalServiceException e) {
				requestAttributes.setAttribute("errors", e.getErrorCodes(), RequestAttributes.SCOPE_REQUEST);
			}
		}
		populateDropDownBean(model);
		return redirectURL;
	}

	@RequestMapping(value = UrlConstants.LIST_PRODUCTS_PAGE, method = { RequestMethod.GET })
	public String listProducts(Model model) {
		try {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			List<ProductDTO> products = catalogueServiceProxy.getProducts(200, 0);
			List<ProductBean> productBeans = new ArrayList<ProductBean>();
			if (products != null && !products.isEmpty()) {
				for (ProductDTO productDTO : products) {
					productBeans.add(produtDataMapper.mapServiceBeanToUIBean(productDTO));
				}
			} else {
				requestAttributes.setAttribute("errors", "No Data Found", RequestAttributes.SCOPE_REQUEST);
			}

			model.addAttribute("productBeans", productBeans);
		} catch (InternalServiceException e) {
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
