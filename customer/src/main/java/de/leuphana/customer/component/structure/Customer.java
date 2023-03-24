package de.leuphana.customer.component.structure;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {

	private static Integer lastGeneratedCustomerId = 0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer customerId;
	@Column(name="customer_name",nullable=false)
	private String name;
	@Column(name="address_id",insertable=false,updatable=false)
	private Integer addressId;
//	private Map<Integer, Order> orders;
	
//	public Customer(Cart cart) {
//		this.customerId = ++lastGeneratedCustomerId;
//		this.cart = cart;
//		//orders = new HashMap<Integer, Order>();
//	}
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", addressId=" + addressId + ", address="
				+ address + "]";
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
}
