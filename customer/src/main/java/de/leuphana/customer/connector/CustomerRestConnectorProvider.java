package de.leuphana.customer.connector;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/shop/customer")
public class CustomerRestConnectorProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRestConnectorProvider.class);

	private CustomerService customerService;

	@Autowired
	private Tracer tracer;

	public CustomerRestConnectorProvider(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
		if (customerId != null) {
			customerService.deleteCustomer(customerId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
		Span span = tracer.spanBuilder("getCustomers-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Getting all customers");
			return customerService.getAllCustomers();
		}catch (EntityNotFoundException e) {
			logger.error("Error getting customers", e);
        }finally {
			span.end();
		}
		return null;
	}

	@GetMapping("/getCustomerById/{customerId}")
	public Customer getCustomerById(@PathVariable Integer customerId) throws Exception{
		Span span = tracer.spanBuilder("getCustomerById-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Getting customer with id {}: ", customerId);
			return customerService.getCustomerById(customerId);
		}catch (EntityNotFoundException e) {
			logger.error("Error getting customer with id {}: ", customerId, e);
        }finally {
			span.end();
		}
		return null;
	}

	@PutMapping("/updateCustomerById/{customerId}")
	public Customer updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer) throws Exception{
		Span span = tracer.spanBuilder("updateCustomerById-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Updating customer with id {}: ", customerId);
			return customerService.updateCustomerById(customerId, customer);
		}catch (EntityNotFoundException e) {
			logger.error("Error updating customer with id {}: ", customerId, e);
        }finally {
			span.end();
		}
		return null;

	}

}
