package com.inncretech.catalogue.service;

import java.util.List;

import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.InternalServiceException;
import com.inncretech.catalogue.exceptions.InvalidArgumentException;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;

public interface ProductService {

	ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException;

	ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException;

	List<ProductDTO> getActiveProducts(int limit, int offset) throws InvalidArgumentException, InternalServiceException;

	ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException;

	ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException;

	Boolean deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException;

	Boolean markProductsInActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

	Boolean markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

	Boolean markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException;

}