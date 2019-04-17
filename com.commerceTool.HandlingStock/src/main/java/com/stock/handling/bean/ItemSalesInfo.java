package com.stock.handling.bean;

import java.sql.Timestamp;

public class ItemSalesInfo {

	String productID;
	int itemSold;
	Timestamp date;

	public ItemSalesInfo(String productID, int itemSold, Timestamp date) {
		this.productID = productID;
		this.itemSold = itemSold;
		this.date = date;
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

}
