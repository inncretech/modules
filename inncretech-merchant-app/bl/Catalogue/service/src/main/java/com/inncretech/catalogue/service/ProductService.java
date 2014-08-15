package com.inncretech.catalogue.service;

import java.util.List;

import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;

public interface ProductService {

	ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException;

	ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException;

	List<ProductDTO> getActiveProducts(Integer merchantId, int limit, int offset) throws InvalidArgumentException,
			InternalServiceException;

	ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException;

	ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException;

	void deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException;

	void markProductsInActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

	void markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

	void markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

}