package de.leuphana.order.component.structure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "orderId")
@Entity
@Table(name = "orders")
public class Order{
	
	@Id
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(name = "CustomerID", nullable = false)
	private Integer customerId;
	
	@Column(name = "OrderDate", nullable = false)
	private LocalDate orderDate = LocalDate.now();
	
//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private ArrayList<OrderPosition> orderPositions;
//	
//	public Order() {
//		orderPositions = new ArrayList<OrderPosition>();
//	}
//	
//	@Column(name="order_pos_id")
//	private Long orderPosId;
	
	@OneToMany(mappedBy="order")
	private ArrayList<OrderPosition> orderPositions;
	public Long getOrderId() {
		return orderId;
	}
	
	
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

//
//	public void setOrderPosId(Long orderPosId) {
//		this.orderPosId = orderPosId;
//	}


	public void setOrderPositions(ArrayList<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public ArrayList<OrderPosition> getOrderPositions() {
		return orderPositions;
	}


	public Object getOrderDate() {
		return this.orderDate;
	}
}