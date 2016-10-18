package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDAO {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> findByName(String name) {
		return productRepository.findByName("%" + name + "%");
	}
	public List<Product> findByProductType(int idProductType) {
		return productRepository.findByProductType(idProductType);
	}
}
