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
import com.inncretech.catalogue.db.repository.ItemRepository;
import com.inncretech.catalogue.db.repository.ProductRepository;
import com.inncretech.catalogue.dto.ItemDTO;
import com.inncretech.catalogue.dto.ProductDTO;
import com.inncretech.catalogue.exceptions.InternalServiceException;
import com.inncretech.catalogue.exceptions.InvalidArgumentException;
import com.inncretech.catalogue.exceptions.ProductNotFoundException;
import com.inncretech.catalogue.service.ProductService;
import com.inncretech.catalogue.service.utils.mapper.CatalogueDozerMapper;
import com.inncretech.catalogue.validators.ProductServiceValidator;

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

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException {

		validator.doValidate(productDTO);
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

	@Override
	@Transactional
	public ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		validator.doValidateProductId(productId);
		Product product = null;
		try {
			product = productRepository.getOne(productId);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new ProductNotFoundException();
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
		ProductDTO productDTO = new ProductDTO();
		mapper.mapProductToProductDTO(product, productDTO);
		return productDTO;
	}

	@Override
	@Transactional
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

	@Override
	@Transactional
	public ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		return null;
	}

	@Override
	public ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException {
		return null;
	}

	@Override
	@Transactional
	public void deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException {
		validator.doValidateItemList(itemIds);
		try {
			List<Item> items = itemRepository.findByItemIds(itemIds);
			for (Item item : items) {
				item.setIsActive(false);
			}
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
	}

	@Override
	public void markProductsInActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
	}

	@Override
	public void markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
	}

	@Override
	public void markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
	}

}