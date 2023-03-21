package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/shop/customers")
public class CustomerRestConnectorProvider {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerRestConnectorProvider(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/getCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

	@GetMapping("/getCustomerById/{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) throws Exception {
        return customerService.getCustomerById(customerId);
    }

	@PutMapping("/updateCustomerById/{customerId}")
    public Customer updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer) throws Exception {
		return customerService.updateCustomerById(customerId, customer);
        
    }

	@DeleteMapping("/deleteCustomerById/{customerId}")
    public void deleteCustomerById(@PathVariable Integer customerId) throws Exception {
        customerService.deleteCustomerById(customerId);
        
    }

}
