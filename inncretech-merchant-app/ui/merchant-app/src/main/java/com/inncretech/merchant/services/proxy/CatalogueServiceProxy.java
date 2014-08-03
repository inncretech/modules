package com.inncretech.merchant.services.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.InternalServiceException;
import com.inncretech.catalogue.exceptions.InvalidArgumentException;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.catalogue.service.ProductService;

@Service
public class CatalogueServiceProxy {

	@Autowired
	private ProductService productService;

	public ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException {
		return productService.addProduct(productDTO);
	}

	public List<ProductDTO> getProducts(int limit, int offset) throws InternalServiceException,
			InvalidArgumentException {
		return productService.getActiveProducts(limit, offset);
	}

	public ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		return productService.getProductByProductId(productId);
	}

	public ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		return productService.updateProduct(productDTO);
	}

	public ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException {
		return productService.addItemToProduct(productId, itemDTO);
	}

	public void deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException {
		productService.deleteItems(itemIds);
	}

	public void markProductsInActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		productService.markProductsInActive(productIds);
	}

	public void markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		productService.markProductsActive(productIds);
	}

	public void markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		productService.markProductsDeleted(productIds);
	}

}
