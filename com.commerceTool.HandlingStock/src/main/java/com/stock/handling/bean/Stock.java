package com.stock.handling.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Stock{

	String id;
	String timeStamp;
	int quantity;

	public Stock() {
		super();
	}
	public Stock(String id,String timeStamp, int quantity) {
		super();
		this.id = id;
		this.timeStamp=timeStamp;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "stock [id=" + id  +  "]";
	}


}