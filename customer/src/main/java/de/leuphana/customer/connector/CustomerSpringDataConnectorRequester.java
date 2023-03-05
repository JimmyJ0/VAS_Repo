package de.leuphana.customer.connector;

import java.net.URI;
import java.util.List;
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

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerSpringDataConnectorRequester {
	
	private CustomerRepository customerRepository;
//	private CustomerService customerService;

	@Autowired
	public CustomerSpringDataConnectorRequester(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	// 2.
	// Erhält HTML mit als JSON verpacktem Artikel vom CustomerRestConnector
	// Ruft anschließend den Service auf und speichert den Kunden in Datenbank
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		System.out.println("... Erhalte Kunde und ...");
		Customer savedCustomer = customerRepository.save(customer);
		return ResponseEntity.created(URI.create("/customers/" + savedCustomer.getCustomerId())).body(savedCustomer);
	}
	
	@GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer updatedCustomer = customerOptional.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer = customerRepository.save(updatedCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
