package com.example.demo.dao;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
@Transactional
public class ProductDaoImp {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Product> list= session.createCriteria(Product.class).list();
		return list;
	}
	
	public Product findById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Product product=(Product) session.get(Product.class,id);
		return product;
	}
	
	public void add(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(product); 
	}
	
	public Product update(Product val, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Product product =(Product)session.get(Product.class, id);
		product.setPrice(val.getPrice());
		product.setName(val.getName());
		session.update(product);
		return product;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Product product = findById(id);
		session.delete(product);
	}
	
}
