package de.leuphana.order.connector;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.component.structure.Order;

@RestController
@RequestMapping("/orders")
public class OrderSpringDataConnectorRequester {

    private final OrderService orderService;

    @Autowired
    public OrderSpringDataConnectorRequester(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.created(URI.create("/orders/" + savedOrder.getOrderId())).body(savedOrder);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getCustomerById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrder(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            Order existingOrder = orderService.getOrder(id);
            existingOrder.setCustomerId(order.getCustomerId());
            existingOrder.setOrderPositions(order.getOrderPositions());
            existingOrder = orderService.createOrder(existingOrder);
            return ResponseEntity.ok(existingOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.getOrder(id);
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
