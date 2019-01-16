
package com.example.demo.controller;
  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
  
  @RestController 
  public class ProductController {
  
	  @Autowired
	  ProductService productService;
	  
	  @RequestMapping("/") 
	  public ResponseEntity<List> index() { 
		  
		  List products = productService.getProducts();
		  
		  return new ResponseEntity<List>(products,HttpStatus.OK);
	  }

	  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
	        System.out.println("Fetching product with id " + id);
	        Product product = productService.findById(id);
	        if (product == null) {
	            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Product>(product, HttpStatus.OK);
	  }
	    
		 @PostMapping(value="/create",headers="Accept=application/json")
		 public ResponseEntity<Void> create(@RequestBody Product product, UriComponentsBuilder ucBuilder){
		     System.out.println("Creating product "+ product.getName());
		     productService.create(product);
		     HttpHeaders headers = new HttpHeaders();
		     headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
		     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		 }

		 @GetMapping(value="/get", headers="Accept=application/json")
		 public List<Product> getAllProducts() {
			 List<Product> products = productService.getProducts();
			 return products;
		
		 }

		@PutMapping(value="/update", headers="Accept=application/json")
		public ResponseEntity<String> update(@RequestBody Product currentProduct)
		{
			Product product = productService.findById(currentProduct.getId());
			if (product == null) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			productService.update(currentProduct, currentProduct.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		
		@DeleteMapping(value="/{id}", headers ="Accept=application/json")
		public ResponseEntity<Product> delete(@PathVariable("id") int id){
			Product product = productService.findById(id);
			if (product == null) {
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			}
			productService.delete(id);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
  
  }
 