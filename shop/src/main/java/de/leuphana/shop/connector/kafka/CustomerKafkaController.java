package de.leuphana.shop.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.Customer;

@RestController
@RequestMapping("/shop/customer")
public class CustomerKafkaController {
	
	private CustomerKafkaProducer customerKafkaProducer;
	private ShopService shopService;
	
	@Autowired
	public CustomerKafkaController(CustomerKafkaProducer customerKafkaProducer,
			ShopService shopService) {
		super();
		this.customerKafkaProducer = customerKafkaProducer;
		this.shopService = shopService;
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		customerKafkaProducer.sendCustomer(customer);
		return ResponseEntity.ok("customer created");
	}
	
	
	@PostMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteArticle(@PathVariable Integer customerId) {
		customerKafkaProducer.deleteCustomer(customerId);
		return ResponseEntity.ok("customer deleted");
	}

}
