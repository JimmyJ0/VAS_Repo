package de.leuphana.customer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;

@RestController
@RequestMapping("/shop/customer")
public class CustomerKafkaController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerKafkaProducer customerKafkaProducer;


	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.createCustomer(customer);
		customerKafkaProducer.sendCustomer(newCustomer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
		if (customerId != null) {
			customerService.deleteCustomer(customerId);
			customerKafkaProducer.deleteCustomer(customerId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
