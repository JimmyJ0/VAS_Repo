package de.leuphana.order.connector;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/orders")	
public class OrderSpringDataConnectorRequester {
	
		private OrderService orderService;

		@Autowired
		public OrderSpringDataConnectorRequester(OrderService orderService) {
			super();
			this.orderService = orderService;
		}
		
		//Mit Postman können Anfragen im JSON Format direkt an den RestController geschickt werden
		//Wenn dies erfolgte, werden die entsprechenden Annotationen angesprochen, wie z.B. @PostMapping
		//Dann wird die entsprechende Methode (hier: createCustomer) ausgeführt, die von CustomerService stammt 
		//Anschließend werden auf die CRUD Operationen von JpaRepository zugegriffen, um mit der Datenbank zu kommunizieren
		
		//POST -> http://localhost:9090/customers (Erstellung eines Kunden mit "customerId", "name" und "address")
		@PostMapping
		public ResponseEntity<Order> createOrder(@RequestBody Order order) {
			System.out.println("... Erhalte Bestellung und ...");
			Order savedOrder = orderService.createOrder(order);
			return ResponseEntity.created(URI.create("/orders/" + savedOrder.getOrderId())).body(savedOrder);
		}
		//GET-> http://localhost:9090/customers (gibt alle Kunden aus)
//		@GetMapping
//	    public List<Order> getAllOrders() {
//	        return orderService.getAllCustomers();
//	    }
		//GET-> http://localhost:9090/customers/3 (gibt Kunde mit customerID=3 aus)
		@GetMapping("/{id}")
	    public ResponseEntity<Order> getCustomerById(@PathVariable Long id) throws Exception {
	        Optional<Order> customerOptional = Optional.of(orderService.getOrder(id));
	        return customerOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
		
		//PUT-> http://localhost:9090/customers/3 (Für Kunde mit customerId=3 können "name" und "address" mit Postman angepasst werden)
		@PutMapping("/{id}")
	    public ResponseEntity<Order> updateCustomer(@PathVariable Long id, @RequestBody Order order) throws Exception {
	        Optional<Order> orderOptional = Optional.of(orderService.getOrder(id));
	        if (orderOptional.isPresent()) {
	            Order updatedCustomer = orderOptional.get();
//	            updatedCustomer.setName(order.getName());
//	            updatedCustomer.setAddress(order.getAddress());
	            updatedCustomer = orderService.createOrder(updatedCustomer);
	            return ResponseEntity.ok(updatedCustomer);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		//DELETE-> http://localhost:9090/customers/3 (löscht Kunde mit customerId=3 in der Datenbank)
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws Exception {
	        Optional<Order> orderOptional = Optional.of(orderService.getOrder(id));
	        if (orderOptional.isPresent()) {
	        	orderService.deleteOrder(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	}
