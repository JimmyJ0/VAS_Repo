package de.leuphana.customer.component.structure;

import java.util.HashMap;
import java.util.Map;

import de.leuphana.shop.structure.sales.Cart;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	private static Integer lastGeneratedCustomerId = 0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer customerId;
	@Column(name="customer_name", nullable=false)
	private String name;
	@Column(name="customer_address", nullable=false)
	private String address;
//	@Column(name="customer_cart", nullable=false)
	private Cart cart;
//	@Column(name="customer_orders", nullable=false)
//	private Map<Integer, Order> orders;
	
//	public Customer(Cart cart) {
//		this.customerId = ++lastGeneratedCustomerId;
//		this.cart = cart;
//		//orders = new HashMap<Integer, Order>();
//	}

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
	
//	public void addOrder(Order order) {
//	orders.put(order.getOrderId(), order);
//}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
