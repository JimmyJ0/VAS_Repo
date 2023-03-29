package de.leuphana.shop.connector.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.sales.Customer;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;


@RestController
@RequestMapping("/shop/customer")
public class CustomerRestConnectorRequester {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerRestConnectorRequester.class);

	private ShopService shopService;

	@Autowired
	private Tracer tracer;

	@Autowired
	public CustomerRestConnectorRequester(ShopService shopService) {
		this.shopService = shopService;
	}

	@PostMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer){
		Span span = tracer.spanBuilder("createCustomer-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
			ResponseEntity<Customer> response = restTemplate.exchange(
					"http://api-gateway:9000/shop/customer/createCustomer", HttpMethod.POST, requestEntity,
					Customer.class, customer);
			try {
				LOGGER.info("Creating customer: {}", customer);
				Customer createdCustomer = response.getBody();
				LOGGER.info("Created customer: {}", customer);
				return createdCustomer;

			}catch (Exception e) {
				LOGGER.error("Error creating customer: {}", e);
			}finally {
				span.end();
			}
			return null;
		}
	}

	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
		Span span = tracer.spanBuilder("getAllCustomers-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<List<Customer>> response = restTemplate.exchange(
					"http://api-gateway:9000/shop/customer/getCustomers", HttpMethod.GET, requestEntity, 
					new ParameterizedTypeReference<List<Customer>>() {
					});
			try {
				LOGGER.info("Getting all customers");
				List<Customer> customers = response.getBody();
				LOGGER.info("Retrieved all customers");
				shopService.insertCustomers(customers);
				return customers;

			}catch (Exception e) {
				LOGGER.error("Error retrieving all customers", e);
			}finally {
				span.end();
			}
			return null;
		}
	}

	@GetMapping("/getCustomerById/{customerId}")
	public Customer getCustomerById(@PathVariable Integer customerId){
		Span span = tracer.spanBuilder("getCustomerById-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Integer> requestEntity = new HttpEntity<>(customerId, headers);
			ResponseEntity<Customer> response = restTemplate.exchange(
					"http://api-gateway:9000/shop/customer/getCustomerById/{customerId}", HttpMethod.GET, requestEntity,
					Customer.class, customerId);
			try {
				LOGGER.info("Getting customer with id {}", customerId);
				Customer customerById = response.getBody();
				LOGGER.info("Retrieved customer with id {}", customerId);
				return customerById; 
			}catch (Exception e) {
				LOGGER.error("Error retrieving customer by id {}", customerId, e);
			}finally {
				span.end();
			}
			return null;
		}
	}

	@PutMapping("/updateCustomerById/{customerId}")
	public Customer updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer){
		Span span = tracer.spanBuilder("updateCustomerById-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
			ResponseEntity<Customer> response = restTemplate.exchange(
					"http://api-gateway:9000/shop/customer/updateCustomerById/{customerId}", HttpMethod.PUT, requestEntity,
					Customer.class, customerId);
			try {
				LOGGER.info("Updating customer with id {}", customerId);
				Customer updatedCustomer = response.getBody();
				LOGGER.info("Updated customer with id {}", customerId);
				return updatedCustomer;
			}catch (Exception e) {
				LOGGER.error("Error updating customer with id {}", customerId, e);
			}finally {
				span.end();
			}
			return null;
		}
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public void deleteCustomer(@PathVariable Integer customerId){
		Span span = tracer.spanBuilder("deleteCustomerById-span").startSpan();
		try (Scope scope = span.makeCurrent()){
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Integer> requestEntity = new HttpEntity<>(customerId, headers);
			ResponseEntity<Void> response = restTemplate.exchange(
					"http://api-gateway:9000/shop/customer/deleteCustomer/{customerId}", HttpMethod.DELETE, requestEntity,
					Void.class, customerId);
			try {
				LOGGER.info("Deleting customer with id {}", customerId);
				response.getBody();
				LOGGER.info("Deleted customer with id {}", customerId);

			}catch (Exception e) {
				LOGGER.error("Error deleting customer wit id {}", customerId, e);
			}finally {
				span.end();
			}
			
		}

	}

}
