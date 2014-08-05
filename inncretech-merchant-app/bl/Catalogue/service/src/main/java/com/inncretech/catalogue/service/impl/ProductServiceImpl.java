package com.inncretech.catalogue.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inncretech.catalogue.db.beans.Item;
import com.inncretech.catalogue.db.beans.Product;
import com.inncretech.catalogue.db.enums.Status;
import com.inncretech.catalogue.db.repository.ItemRepository;
import com.inncretech.catalogue.db.repository.ProductRepository;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.catalogue.service.ProductService;
import com.inncretech.catalogue.service.utils.mapper.CatalogueDozerMapper;
import com.inncretech.catalogue.validators.ProductServiceValidator;
import com.inncretech.common.exceptions.InternalServiceException;
import com.inncretech.common.exceptions.InvalidArgumentException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ProductServiceValidator validator;

	@Autowired
	private CatalogueDozerMapper mapper;

	@Transactional
	@Override
	public ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException {

		validator.doValidateProductDTO(productDTO);
		Product product = new Product();
		mapper.mapProductDTOToProduct(productDTO, product);
		try {
			productRepository.save(product);
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
		ProductDTO resultProductDTO = new ProductDTO();
		mapper.mapProductToProductDTO(product, resultProductDTO);
		return resultProductDTO;
	}

	@Transactional
	@Override
	public ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		Product product = getProductDBModelByProductId(productId);
		ProductDTO productDTO = new ProductDTO();
		mapper.mapProductToProductDTO(product, productDTO);
		return productDTO;
	}

	@Transactional
	@Override
	public List<ProductDTO> getActiveProducts(int limit, int offset) throws InvalidArgumentException,
			InternalServiceException {
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		PageRequest page = new PageRequest((offset / limit), limit);
		Page<Product> products = productRepository.findAll(page);
		Iterator<Product> productsItr = products.iterator();
		while (productsItr.hasNext()) {
			Product product = productsItr.next();
			ProductDTO productDTO = new ProductDTO();
			mapper.mapProductToProductDTO(product, productDTO);
			productList.add(productDTO);
		}
		return productList;
	}

	@Transactional
	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		validator.doValidateProductDTO(productDTO);
		// Product product = new Product();
		// mapper.mapProductDTOToProduct(productDTO, product);
		//
		return null;
	}

	@Transactional
	@Override
	public ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException {
		validator.doValidateItemDTO(itemDTO);
		Product product = getProductDBModelByProductId(productId);
		Item item = mapper.convertItemDTOIntoItem(itemDTO);
		item.setProduct(product);
		product.getItems().add(item);
		ProductDTO productDTO = new ProductDTO();
		mapper.mapProductToProductDTO(product, productDTO);
		return productDTO;
	}

	@Transactional
	@Override
	public void deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException {
		validator.doValidateListOfLong(itemIds);
		try {
			itemRepository.deleteItemsByItemIds(itemIds);
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
	}

	@Transactional
	@Override
	public void markProductsInActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		setProductsStatus(Status.INACTIVE, productIds);
	}

	@Transactional
	@Override
	public void markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		setProductsStatus(Status.ACTIVE, productIds);
	}

	@Override
	public void markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		validator.doValidateListOfLong(productIds);
		try {
			productRepository.markProductDeletedByProductIds(productIds);
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
	}

	void setProductsStatus(Status status, List<Long> productIds) throws InvalidArgumentException,
			InternalServiceException {
		validator.doValidateListOfLong(productIds);
		Iterable<Product> iterable = null;
		try {
			iterable = productRepository.findAll(productIds);
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
		Iterator<Product> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			product.setStatus(status);
		}
	}

	Product getProductDBModelByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		validator.doValidateProductId(productId);
		try {
			return productRepository.getOne(productId);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new ProductNotFoundException();
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
	}
}