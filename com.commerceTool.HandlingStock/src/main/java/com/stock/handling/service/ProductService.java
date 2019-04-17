package com.stock.handling.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.stock.handling.bean.ItemResponse;
import com.stock.handling.bean.ItemSalesInfo;
import com.stock.handling.bean.Product;
import com.stock.handling.bean.ProductResponse;
import com.stock.handling.utils.DateUtil;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class ProductService {

	public static HashMap<String,Product> productIdMap=getProductIdMap();
	public static Set<ItemSalesInfo> sales = new HashSet<>();

	public ProductService() {
		super();

		if(productIdMap==null)
		{
			productIdMap=new HashMap<String,Product>();
			// Creating some objects of Country while initializing
			Product indiaCountry=new Product("1",new Timestamp(new Date().getTime()).toString(),"book",10000);
			Product chinaCountry=new Product("2",new Timestamp(new Date().getTime()).toString() ,"pen",20000);
			Product nepalCountry=new Product( "3",new Timestamp(new Date().getTime()).toString(),"mobile",8000);
			Product bhutanCountry=new Product( "4",new Timestamp(new Date().getTime()).toString(),"mobile-123",7000);

			productIdMap.put(indiaCountry.getProductID(),indiaCountry);
			productIdMap.put(chinaCountry.getProductID(),chinaCountry);
			productIdMap.put(nepalCountry.getProductID(),nepalCountry);
			productIdMap.put(bhutanCountry.getProductID(),bhutanCountry);
		}

		if(sales.size()==0)
		{
			int max = 100;
			int min = 1;
			sales.add(new ItemSalesInfo("pen",(int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("mobile", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("book", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("pen", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("mobile", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("book", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("mobile-123", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));
			sales.add(new ItemSalesInfo("mobile-123", (int)(Math.random()*((max-min)+1))+min, new Timestamp(new Date().getTime())));

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			sales.add(new ItemSalesInfo("pen",(int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("mobile", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("book", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("pen", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("mobile", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("book", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("mobile-123", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));
			sales.add(new ItemSalesInfo("mobile-123", (int)(Math.random()*((max-min)+1))+min, new Timestamp(cal.getTime().getTime())));

		}
	}

	public List<Product> getAllproducts()
	{
		List<Product> products = new ArrayList<Product>(productIdMap.values());
		return products;
	}


	public ResponseEntity<Object> getProduct(String productId)
	{
		Product product = productIdMap.get(productId);
		ProductResponse response = new ProductResponse(product.getId(), product.getTimeStamp(), product.getProductID()	,product.getQuantity());
		response.setRequestTimeStamp();
		return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
	}

	public Product addProduct(Product product)
	{
		productIdMap.put(product.getProductID(), product);

		return product;
	}

	public static HashMap<String, Product> getProductIdMap() {
		return productIdMap;
	}

	public boolean isAvailable(String productID)
	{
		if(productIdMap.containsKey(productID))
			return true;

		return false;
	}

	public List<Product> getTopAvailable()
	{
		List<Product> topAvailableProduct = new ArrayList<>();
		Map<Integer,Product> quantitys = new TreeMap<>(Collections.reverseOrder());
		for(Product product: productIdMap.values())
			quantitys.put(product.getQuantity(), product);

		int i=1;
		for(Entry<Integer, Product> key : quantitys.entrySet())
		{
			topAvailableProduct.add(key.getValue());
			i++;
			if(i>3)
				break;
		}

		return topAvailableProduct;
	}

	public List<ItemResponse> gettopSoldProducts(String range)
	{
		List<ItemResponse> items = new ArrayList<>();
		Map<String, Integer> productsSold = new HashMap<>();
		if(range.equals("today"))
		{

			for(ItemSalesInfo item : sales)
			{
				if(DateUtil.isSameDay(new Date(), item.getDate()))
				{
					int itemsSold = 0;
					if(productsSold.containsKey(item.getProductID())){
						itemsSold+=productsSold.get(item.getProductID());
					}
					productsSold.put(item.getProductID(), itemsSold+item.getItemSold());
				}
			}
		}
		else if(range.equals("lastMonth"))
		{
			
			Date startDate, endDate;
			
			startDate = DateUtil.getBeginningOfMonth();
			endDate = DateUtil.getEndOfMonth();
			
			for(ItemSalesInfo item : sales)
			{
				if(DateUtil.isWithinRange(item.getDate(), startDate, endDate))
				{
					int itemsSold = 0;
					if(productsSold.containsKey(item.getProductID())){
						itemsSold+=productsSold.get(item.getProductID());
					}
					productsSold.put(item.getProductID(), itemsSold+item.getItemSold());
			
				}
			}
		}

		List<Entry<String, Integer>> sortedMap = entriesSortedByValues(productsSold);
		
		int i =1;
		for(Entry<String, Integer> itemSold: sortedMap)
		{
			items.add(new ItemResponse(itemSold.getKey(),itemSold.getValue()));
			i++;
			if(i>3)
				break;
		}

		return items;

	}

	static <K,V extends Comparable<? super V>> 
	List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

		Collections.sort(sortedEntries, 
				new Comparator<Entry<K,V>>() {
			@Override
			public int compare(Entry<K,V> e1, Entry<K,V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		}
				);

		return sortedEntries;
	}


}

