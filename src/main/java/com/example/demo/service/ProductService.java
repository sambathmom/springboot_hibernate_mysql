package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDaoImp;
import com.example.demo.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDaoImp productDaoImp;
	
	public List getProducts() {
		return productDaoImp.getProducts();
	}
	
	public Product findById(int id)
	{
		return productDaoImp.findById(id);
	}
	
	public void create(Product product) {
		productDaoImp.add(product);
	}
	
	public Product update(Product product, int id) {
		return productDaoImp.update(product, id);
	}
	
	public void delete(int id) {
		productDaoImp.delete(id);
	}

}
