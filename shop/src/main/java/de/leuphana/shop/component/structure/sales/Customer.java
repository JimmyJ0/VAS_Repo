package de.leuphana.shop.component.structure.sales;

import java.util.Map;

//import org.springframework.core.annotation.Order;

public class Customer {
	private static Integer lastGeneratedCustomerId = 0;

	private long customerId;
	private String name;
	private String address;
	private Cart cart;
	//private Map<Long, Order> orders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Map<Integer, Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Map<Integer, Order> orders) {
//		this.orders = orders;
//	}

	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

//	public Customer(Cart cart) {
//		this.customerId = ++lastGeneratedCustomerId;
//		this.cart = cart;
//		orders = new HashMap<Integer, Order>();
//	}

	
//	public void addOrder(Order order) {
//		orders.put(order.getOrderId(), order);
//	}
}
