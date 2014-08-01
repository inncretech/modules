package com.tastetablet.catalogue.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tastetablet.catalogue.db.beans.Product;
import com.tastetablet.catalogue.db.repository.ProductRepository;
import com.tastetablet.catalogue.dto.ItemDTO;
import com.tastetablet.catalogue.dto.ProductDTO;
import com.tastetablet.catalogue.exceptions.InternalServiceException;
import com.tastetablet.catalogue.exceptions.InvalidArgumentException;
import com.tastetablet.catalogue.exceptions.ProductNotFoundException;
import com.tastetablet.catalogue.service.ProductService;
import com.tastetablet.catalogue.service.utils.mapper.CatalogueDozerMapper;
import com.tastetablet.catalogue.validators.ProductServiceValidator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductServiceValidator productValidator;

	@Autowired
	private CatalogueDozerMapper mapper;

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgumentException, InternalServiceException {

		productValidator.doValidate(productDTO);
		try {
			Product product = new Product();
			mapper.mapDTOToDBBean(productDTO, product);
			productRepository.save(product);
			ProductDTO resultProductDTO = new ProductDTO();
			mapper.mapBeanToDTO(product, resultProductDTO);
			return resultProductDTO;
		} catch (Exception exception) {
			throw new InternalServiceException();
		}
	}

	@Override
	@Transactional
	public ProductDTO getProductByProductId(Long productId) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
		productValidator.doValidateProductId(productId);
		Product product = null;
		try {
			product = productRepository.getOne(productId);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new ProductNotFoundException();
		}
		try {
			ProductDTO productDTO = new ProductDTO();
			mapper.mapBeanToDTO(product, productDTO);
			return productDTO;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new InternalServiceException();
		}
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
			mapper.mapBeanToDTO(product, productDTO);
			productList.add(productDTO);
		}
		return productList;
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(ProductDTO productDTO) throws InvalidArgumentException, ProductNotFoundException,
			InternalServiceException {
//		productValidator.doValidate(productDTO);
//		try {
//			Product product = new Product();
//			mapper.mapDTOToDBBean(productDTO, product);
//			productRepository.save(product);
//			ProductDTO resultProductDTO = new ProductDTO();
//			mapper.mapBeanToDTO(product, resultProductDTO);
//			return resultProductDTO;
//		} catch (Exception exception) {
//			throw new InternalServiceException();
//		}
		return null;
	}

	@Override
	public ProductDTO addItemToProduct(Long productId, ItemDTO itemDTO) throws InvalidArgumentException,
			ProductNotFoundException, InternalServiceException {
		return null;
	}

	@Override
	public Boolean deleteItems(List<Long> itemIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean markProductsInActive(List<Long> productIds) throws InvalidArgumentException,
			InternalServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean markProductsActive(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean markProductsDeleted(List<Long> productIds) throws InvalidArgumentException, InternalServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}