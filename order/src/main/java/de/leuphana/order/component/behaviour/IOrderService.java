package de.leuphana.order.component.behaviour;

import java.util.Optional;

import de.leuphana.order.component.structure.Order;

public interface IOrderService {
	Order createOrder(Order order);
	Order getOrder (Long id) throws Exception;
	//Order updateOrderStatus(Long id);
	void deleteOrder(Long id);
}
