package de.leuphana.order.component.behaviour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.order.component.structure.Order;
import de.leuphana.order.component.structure.OrderPosition;
import de.leuphana.order.configuration.OrderPositionRepository;
import de.leuphana.order.configuration.OrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private OrderPositionRepository orderPositionRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
    }

    @Override
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        for (OrderPosition orderPosition : order.getOrderPositions()) {
            orderPosition.setOrder(savedOrder);
        }

        orderPositionRepository.saveAll(order.getOrderPositions());

        return savedOrder;
    }

    @Override
    public Order getOrder(Long id) throws Exception {
        return orderRepository.findById(id)
                .orElseThrow(() -> new Exception("... order with id " + id + " not found."));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		return orderRepository.findByCustomerId(customerId);
	}
}
