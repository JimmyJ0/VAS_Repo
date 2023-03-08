package de.leuphana.customer.connector;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/customers")
public class CustomerRestConnectorProvider {
	
	private CustomerService customerService;

	@Autowired
	public CustomerRestConnectorProvider(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	//Mit Postman können Anfragen im JSON Format direkt an den RestController geschickt werden
	//Wenn dies erfolgte, werden die entsprechenden Annotationen angesprochen, wie z.B. @PostMapping
	//Dann wird die entsprechende Methode (hier: createCustomer) ausgeführt, die von CustomerService stammt 
	//Anschließend werden auf die CRUD Operationen von JpaRepository zugegriffen, um mit der Datenbank zu kommunizieren
	
	//POST -> http://localhost:9090/customers (Erstellung eines Kunden mit "customerId", "name" und "address")
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		System.out.println("... Erhalte Kunde und ...");
		Customer savedCustomer = customerService.createCustomer(customer);
		return ResponseEntity.created(URI.create("/customers/" + savedCustomer.getCustomerId())).body(savedCustomer);
	}
	//GET-> http://localhost:9090/customers (gibt alle Kunden aus)
	@GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
	//GET-> http://localhost:9090/customers/3 (gibt Kunde mit customerID=3 aus)
	@GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) throws Exception {
        Optional<Customer> customerOptional = Optional.of(customerService.getCustomerById(customerId));
        return customerOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	//PUT-> http://localhost:9090/customers/3 (Für Kunde mit customerId=3 können "name" und "address" mit Postman angepasst werden)
	@PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) throws Exception {
        Optional<Customer> customerOptional = Optional.of(customerService.getCustomerById(customerId));
        if (customerOptional.isPresent()) {
            Customer updatedCustomer = customerOptional.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer = customerService.createCustomer(updatedCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	//DELETE-> http://localhost:9090/customers/3 (löscht Kunde mit customerId=3 in der Datenbank)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) throws Exception {
        Optional<Customer> customerOptional = Optional.of(customerService.getCustomerById(customerId));
        if (customerOptional.isPresent()) {
        	customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
