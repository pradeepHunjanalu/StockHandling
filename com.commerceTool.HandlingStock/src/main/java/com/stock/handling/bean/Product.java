package com.stock.handling.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Product {
	
	String id;
	String timeStamp;
	String productID;
	int quantity;
	
	public Product() {
		super();
	}
	
	public Product(String id,String timeStamp, String productID, int quantity) {
		super();
		this.productID = productID;
		this.id = id;
		this.timeStamp = timeStamp;
		this.quantity = quantity;
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
