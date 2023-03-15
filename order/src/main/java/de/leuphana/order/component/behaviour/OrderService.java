package de.leuphana.order.component.behaviour;

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
		// TODO Auto-generated method stub
		return orderRepository.save(order);
		
	}

	@Override
	public Order getOrder(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (orderOptional.isPresent()) {
			System.out.println("... get order with id " + id);
			return orderOptional.get();
		} else {
			throw new Exception("... order with id " + id + " not found.");
		}
	}

//	@Override
//	public Order updateOrderStatus(Long id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		System.out.println("... delete order with id " + id);
		orderRepository.deleteById(id);
	}
	
	
}