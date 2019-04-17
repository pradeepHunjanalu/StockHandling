package com.stock.handling.bean;

public class ItemResponse {
	
	String productID;
	int itemSold;
	
	public ItemResponse(String productID,int itemSold ) {
		this.productID = productID;
		this.itemSold = itemSold;
	}
	
	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public int getItemSold() {
		return itemSold;
	}
	
	public void setItemSold(int itemSold) {
		this.itemSold = itemSold;
	}
	
	

}
