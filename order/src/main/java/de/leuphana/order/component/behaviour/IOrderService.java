package de.leuphana.order.component.behaviour;

import de.leuphana.order.component.structure.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(Order order);
    Order getOrder(Long id) throws Exception;
    List<Order> getAllOrders(); // Add this method
    void deleteOrder(Long id);
    List<Order> getOrdersByCustomerId(Integer customerId);
}
