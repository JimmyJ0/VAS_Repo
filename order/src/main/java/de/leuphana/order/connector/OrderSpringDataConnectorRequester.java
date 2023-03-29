package de.leuphana.order.connector;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.component.structure.Order;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
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
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Span span = tracer.spanBuilder("createOrder-span").startSpan();
        try (Scope scope = span.makeCurrent()) {
            logger.info("Creating order");
            Order savedOrder = orderService.createOrder(order);
            span.setAttribute("orderId", String.valueOf(savedOrder.getOrderId()));
            span.setStatus(StatusCode.OK);
            return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error creating order", e);
            span.setStatus(StatusCode.ERROR, "Error creating order");
            return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
        } finally {
            span.end();
        }
    }

	@GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        Span span = tracer.spanBuilder("getAllOrders-span").startSpan();
        try (Scope scope = span.makeCurrent()) {
            logger.info("Retrieving all orders");
            List<Order> orders = orderService.getAllOrders();
            span.setAttribute("ordersCount", orders.size());
            span.setStatus(StatusCode.OK);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            logger.error("Error retrieving all orders", e);
            span.setStatus(StatusCode.ERROR, "Error retrieving all orders");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
			if (order != null) {
				span.setAttribute("orderId", String.valueOf(order.getOrderId()));
				span.setStatus(StatusCode.OK);
				return ResponseEntity.ok(order);
			} else {
				logger.error("Order not found: {}", id);
				span.setStatus(StatusCode.ERROR, "Order not found");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			logger.error("Error retrieving order by id: {}", id, e);
			span.setStatus(StatusCode.ERROR, "Error retrieving order");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
	        if (existingOrder != null) {
	            existingOrder.setCustomerId(order.getCustomerId());
	            existingOrder = orderService.createOrder(existingOrder);
	            span.setAttribute("orderId", String.valueOf(existingOrder.getOrderId()));
	            span.setStatus(StatusCode.OK);
	            return ResponseEntity.ok(existingOrder);
	        } else {
	            logger.error("Order not found: {}", id);
	            span.setStatus(StatusCode.ERROR, "Order not found");
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        logger.error("Error updating order with id: {}", id, e);
	        span.setStatus(StatusCode.ERROR, "Error updating order");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        span.end();
	    }
	}

	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
	    Span span = tracer.spanBuilder("deleteOrder-span").startSpan();
	    try (Scope scope = span.makeCurrent()) {
	        logger.info("Deleting order with id: {}", id);
	        Order order = orderService.getOrder(id);
	        if (order != null) {
	            orderService.deleteOrder(id);
	            span.setAttribute("orderId", String.valueOf(id));
	            span.setStatus(StatusCode.OK);
	            return ResponseEntity.noContent().build();
	        } else {
	            logger.error("Order not found: {}", id);
	            span.setStatus(StatusCode.ERROR, "Order not found");
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        logger.error("Error deleting order with id: {}", id, e);
	        span.setStatus(StatusCode.ERROR, "Error deleting order");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        span.end();
	    }
	}

	@GetMapping("/getOrdersByCustomerId/{customerId}")
	public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Integer customerId) {
	    Span span = tracer.spanBuilder("getOrdersByCustomerId-span").startSpan();
	    try (Scope scope = span.makeCurrent()) {
	        logger.info("Retrieving orders by customer id: {}", customerId);
	        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
	        span.setAttribute("ordersCount", orders.size());
	        span.setStatus(StatusCode.OK);
	        return ResponseEntity.ok(orders);
	    } catch (Exception e) {
	        logger.error("Error retrieving orders by customer id: {}", customerId, e);
	        span.setStatus(StatusCode.ERROR, "Error retrieving orders");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        span.end();
	    }
	}
}