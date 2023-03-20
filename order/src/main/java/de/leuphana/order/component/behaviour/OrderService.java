package de.leuphana.order.component.behaviour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.order.component.structure.Order;
import de.leuphana.order.configuration.OrderRepository;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) throws Exception {
        return orderRepository.findById(id)
                .orElseThrow(() -> new Exception("... order with id " + id + " not found."));
    }

    @Override
    public List<Order> getAllOrders() { // Add this method implementation
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
