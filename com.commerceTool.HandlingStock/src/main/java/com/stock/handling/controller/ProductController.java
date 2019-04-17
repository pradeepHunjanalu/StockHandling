package com.stock.handling.controller;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stock.handling.bean.Product;
import com.stock.handling.bean.ProductResponse;
import com.stock.handling.bean.StasticsResponse;
import com.stock.handling.service.ProductService;

@RestController
public class ProductController {

	ProductService productService = new ProductService();

	@RequestMapping(value = "/products", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Product> getCountries() {
		List<Product> listOfCountries = productService.getAllproducts();
		return listOfCountries;
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET, headers = "Accept=application/json")
	public   ResponseEntity<StasticsResponse> getstatistics( @RequestParam("time") String time) {
		StasticsResponse stastics = new StasticsResponse(time);
		stastics.setRequestTimeStamp();
		stastics.setTopAvailableProducts(productService.getTopAvailable());
		stastics.setTopItemsSold(productService.gettopSoldProducts(time));
		return new ResponseEntity<StasticsResponse>(stastics, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET, headers = "Accept=application/json")
	public   ResponseEntity<Object> getProductById(@RequestParam("productID") String productID) {
		if(!productService.isAvailable(productID))
			return new ResponseEntity<Object>("unable to find the product specified",HttpStatus.BAD_REQUEST);
		return productService.getProduct(productID);
	}
	
	@RequestMapping(value = "/updateStock", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addproduct(@RequestBody Product product) {
		if(productService.isAvailable(product.getProductID()))
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		productService.addProduct(product);
		return  new ResponseEntity<String>("everything went well and the new entry for stock was accepted", HttpStatus.CREATED);
	}

}
