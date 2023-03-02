package de.leuphana.customer.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;

@RestController
@RequestMapping("/shop/customer")
public class CustomerSpringDataConnectorRequester {
	
	private CustomerService customerService;

	@Autowired
	public CustomerSpringDataConnectorRequester(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	// 2.
	// Erhält HTML mit als JSON verpacktem Artikel vom CustomerRestConnector
	// Ruft anschließend den Service auf und speichert den Kunden in Datenbank
	
	@PostMapping()
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		System.out.println("... Erhalte Kunde und ...");

		// Gibt den hinzugefügten Kunden und den HttpStatus.Created zurück
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}

}
