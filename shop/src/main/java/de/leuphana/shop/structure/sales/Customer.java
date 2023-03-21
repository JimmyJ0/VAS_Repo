package de.leuphana.shop.structure.sales;

public class Customer {

	private static Integer lastGeneratedCustomerId = 0;

	private Integer customerId;
	private String name;
//	@Column(name="customer_cart", nullable=false)
//	private Cart cart;
//	@Column(name="customer_orders", nullable=false)
//	private Map<Integer, Order> orders;
	
	public Customer() {

	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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

	
//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
}