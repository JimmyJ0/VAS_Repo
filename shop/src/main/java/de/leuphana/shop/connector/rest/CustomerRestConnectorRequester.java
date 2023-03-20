package de.leuphana.shop.connector.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.shop.structure.sales.Customer;

@RestController
//shop1 müsste geändert werden
@RequestMapping("/shop/shop1")
public class CustomerRestConnectorRequester {

	
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		System.out.println("Sende Kunde weiter...");
		
		// RestTemplate bietet Methoden zum Senden von HTTP-Anfragen und Empfangen von HTTP-Antworten
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation("http://localhost:9090//shop/customer", customer);
		
		// Melded dem Clienten (Hier: Supplier, genauer dem Postman) den HttpStatusCode: CREATED 
		return new ResponseEntity<Customer>(HttpStatus.CREATED);
		
	}

}
