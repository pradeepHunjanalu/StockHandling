package com.stock.handling.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StasticsResponse {

	String  requestTimeStamp;
	String range;
	List<Product> topAvailableProducts ;
	List<ItemResponse> topItemsSold;
	
	public StasticsResponse() {
		super();
	}
	
	public StasticsResponse(String range) {
		topAvailableProducts = new ArrayList<>();
		topItemsSold = new ArrayList<>();
		this.range = range;
	}
	
	
	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}
	
	public void setRequestTimeStamp() {
		this.requestTimeStamp = new Timestamp(new Date().getTime()).toString();
	}
	
	public String getRange() {
		return range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	
	public List<Product> getTopAvailableProducts() {
		return topAvailableProducts;
	}
	
	public void setTopAvailableProducts(List<Product> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}

	public List<ItemResponse> getTopItemsSold() {
		return topItemsSold;
	}

	public void setTopItemsSold(List<ItemResponse> topItemsSold) {
		this.topItemsSold = topItemsSold;
	}
	

}
