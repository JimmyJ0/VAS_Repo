package de.leuphana.shop.connector;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import de.leuphana.shop.structure.sales.Customer;

@RestController
@RequestMapping("/shop/customer")
public class CustomerRestConnectorRequester {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerRestConnectorRequester.class);


	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
	    ResponseEntity<List<Customer>> response = restTemplate.exchange(
	        "http://localhost:9000/shop/customer/getCustomers", HttpMethod.GET, requestEntity, 
	        new ParameterizedTypeReference<List<Customer>>() {
	        });
	    try {
	        LOGGER.info("Getting all customers");
	        List<Customer> customers = response.getBody();
	        LOGGER.info("Retrieved {} customers", customers.size());
	        return customers;
	    } catch (HttpClientErrorException | HttpServerErrorException e) {
	        LOGGER.error("Error retrieving all customers", e);
	        throw e;
	    } catch (Exception e) {
	        LOGGER.error("Unexpected error occurred while retrieving all customers", e);
	        throw e;
	    }
	}

	@GetMapping("/getCustomerById/{customerId}")
	public Customer getCustomerById(@PathVariable Integer customerId){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> requestEntity = new HttpEntity<>(customerId, headers);
		ResponseEntity<Customer> response = restTemplate.exchange(
				"http://localhost:9000/shop/customer/getCustomerById/{customerId}", HttpMethod.GET, requestEntity,
				Customer.class, customerId);
		try {
			LOGGER.info("Getting customer with id {}", customerId);
			Customer customerById = response.getBody();
			LOGGER.info("Retrieved customer with id {}", customerId);
			return customerById;
		} catch(HttpClientErrorException | HttpServerErrorException e) {
			LOGGER.error("Error retrieving customer with id {}", customerId, e);
			throw e;
		} catch (Exception e) {
	        LOGGER.error("Unexpected error occurred while retrieving customer with id {}", customerId, e);
	        throw e;
	    } 

	}

	@PutMapping("/updateCustomerById/{customerId}")
	public Customer updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer){
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
	    ResponseEntity<Customer> response = restTemplate.exchange(
	            "http://localhost:9000/shop/customer/updateCustomerById/{customerId}", HttpMethod.PUT, requestEntity,
	            Customer.class, customerId);
	    try {
	        LOGGER.info("Updating customer with id {}", customerId);
	        Customer updatedCustomer = response.getBody();
	        LOGGER.info("Updated customer with id {}", customerId);
	        return updatedCustomer;
	    } catch(HttpClientErrorException | HttpServerErrorException e) {
	        LOGGER.error("Error updating customer with id {}", customerId, e);
	        throw e;
	    } catch (Exception e) {
	        LOGGER.error("Unexpected error occurred while updating customer with id {}", customerId, e);
	        throw e;
	    } 
	}

}
