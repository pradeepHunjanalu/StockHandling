package com.stock.handling.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.handling.service.ProductService;

public class ProductResponse {

	String productID;
	String  requestTimeStamp;
	Stock stock;

	public ProductResponse(String id,String  timeStamp, String productID, int quantity) {
		super();
		this.productID = productID;
		stock = new Stock(id, timeStamp,quantity);
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}
	
	public void setRequestTimeStamp() {
		this.requestTimeStamp = new Timestamp(new Date().getTime()).toString();
	}

	// Utility method to get max id
	public static int getUniqueID()
	{  
		return ProductService.productIdMap.size()+1;
	}

}
