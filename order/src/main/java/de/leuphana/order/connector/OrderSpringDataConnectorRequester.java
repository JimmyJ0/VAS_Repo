package de.leuphana.order.connector;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.component.structure.Order;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@RestController
@RequestMapping("/shop/order")
public class OrderSpringDataConnectorRequester {

	private static final Logger logger = LoggerFactory.getLogger(OrderSpringDataConnectorRequester.class);

	private final OrderService orderService;

	@Autowired
	private Tracer tracer;

	@Autowired
	public OrderSpringDataConnectorRequester(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping("/createOrder")
	public ResponseEntity<String> createOrder(@RequestBody Order order) {
		Span span = tracer.spanBuilder("createOrder-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Creating order");
			Order savedOrder = orderService.createOrder(order);
			span.setAttribute("orderId", String.valueOf(savedOrder.getOrderId()));
//			return ResponseEntity.created(URI.create("/order/" + savedOrder.getOrderId())).body(savedOrder);
			return new ResponseEntity<String>(HttpStatus.OK);
		} finally {
			span.end();
		}
	}

	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() {
		Span span = tracer.spanBuilder("getAllOrders-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Retrieving all orders");
			List<Order> orders = orderService.getAllOrders();
			span.setAttribute("ordersCount", orders.size());
			return orders;
		} finally {
			span.end();
		}
	}

	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Span span = tracer.spanBuilder("getOrderById-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Retrieving order by id: {}", id);
			Order order = orderService.getOrder(id);
			span.setAttribute("orderId", String.valueOf(order.getOrderId()));
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			logger.error("Order not found: {}", id);
			return ResponseEntity.notFound().build();
		} finally {
			span.end();
		}
	}

	@PutMapping("/updateOrderById/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
		Span span = tracer.spanBuilder("updateOrder-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Updating order with id: {}", id);
			Order existingOrder = orderService.getOrder(id);
			existingOrder.setCustomerId(order.getCustomerId());
			existingOrder.setOrderPositions(order.getOrderPositions());
			existingOrder = orderService.createOrder(existingOrder);
			span.setAttribute("orderId", String.valueOf(existingOrder.getOrderId()));
			return ResponseEntity.ok(existingOrder);
		} catch (Exception e) {
			logger.error("Failed to update order with id: {}", id);
			return ResponseEntity.notFound().build();
		} finally {
			span.end();
		}
	}

	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		Span span = tracer.spanBuilder("deleteOrder-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Deleting order with id: {}", id);
			orderService.getOrder(id);
			orderService.deleteOrder(id);
			span.setAttribute("orderId", String.valueOf(id));
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error("Failed to delete order with id: {}", id);
			return ResponseEntity.notFound().build();
		} finally {
			span.end();
		}
	}
	
	@GetMapping("/getOrdersByCustomerId/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Integer customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
}