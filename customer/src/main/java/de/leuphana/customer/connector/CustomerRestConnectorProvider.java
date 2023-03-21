package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/shop/customers")
public class CustomerRestConnectorProvider {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerRestConnectorProvider(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	//POST -> http://localhost:8000/customers (Erstellung eines Kunden mit "customerId", "name" und "address")
	@PostMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	//GET-> http://localhost:8000/customers (gibt alle Kunden aus)
	@GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
	//GET-> http://localhost:8000/customers/{customerId} (gibt Kunde mit customerID=3 aus)
	@GetMapping("/gestCustomerById/{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) throws Exception {
        return customerService.getCustomerById(customerId);
    }
	//PUT-> http://localhost:8000/customers/{customerId} (Für Kunde mit customerId=3 können "name" und "address" mit Postman angepasst werden)
	@PutMapping("/updateCustomerById/{customerId}")
    public Customer updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) throws Exception {
		customer.setCustomerId(customerId);
		customer.setName(customer.getName());
		customer.setAddress(customer.getAddress());
		customer = customerService.updateCustomerById(customerId, customer);
        return customer;
        
    }
	//DELETE-> http://localhost:8000/customers/{customerId} (löscht Kunde mit customerId=3 in der Datenbank)
	@DeleteMapping("/deleteCustomerById/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) throws Exception {
        customerService.deleteCustomerById(customerId);
        
    }

}
