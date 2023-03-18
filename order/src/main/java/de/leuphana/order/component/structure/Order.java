package de.leuphana.order.component.structure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order{
	
	@Id
	@Column(name = "ID")
	private Long orderId;
	
	@Column(name = "CustomerID", nullable = false)
	private Long customerId;
	
	@Column(name = "OrderDate", nullable = false)
	private LocalDate orderDate = LocalDate.now();
	
	@OneToMany(mappedBy = "order")
    private List<OrderPosition> orderPositions;
	
	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}

	public Object getOrderDate() {
		// TODO Auto-generated method stub
		return this.orderDate;
	}
}