package de.leuphana.customer.connector;

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
		Span span = tracer.spanBuilder("createCustomer-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			logger.info("Creating customer: {}", customer);
			Customer newCustomer = customerService.createCustomer(customer);
			logger.info("Created customer: {}", customer);
			return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
		}catch (EntityNotFoundException e) {
			logger.error("Error creating customer: {}", customer, e);
		}finally {
			span.end();
		}
		return null;
	}

	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
		Span span = tracer.spanBuilder("getCustomers-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			logger.info("Getting all customers");
			List<Customer> allCustomers = customerService.getAllCustomers();
			logger.info("Retrieved all customers");
			return allCustomers;
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
			Customer getCustomer = customerService.getCustomerById(customerId);
			logger.info("Retrieved customer with id {}: ", customerId);
			return getCustomer;
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
			Customer updatedCustomer = customerService.updateCustomerById(customerId, customer);
			logger.info("Updated customer with id {}: ", customerId);
			return updatedCustomer;
		}catch (EntityNotFoundException e) {
			logger.error("Error updating customer with id {}: ", customerId, e);
		}finally {
			span.end();
		}
		return null;

	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
		Span span = tracer.spanBuilder("deleteCustomer-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			if (customerId != null) {
				logger.info("Deleting customer with id {}: ", customerId);
				customerService.deleteCustomer(customerId);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}catch (EntityNotFoundException e) {
			logger.error("Error deleting customer with id {}", customerId, e);
		}finally {
			span.end();
		}
		return null;
	}
}
