package de.leuphana.customer.connector;

import java.util.List;

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
@RequestMapping("/shop/customer")
public class CustomerRestConnectorProvider {
	
	private CustomerService customerService;
	
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
    public Customer getCustomerById(@PathVariable Integer customerId){
        return customerService.getCustomerById(customerId);
    }

	@PutMapping("/updateCustomerById/{customerId}")
    public Customer updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer){
		return customerService.updateCustomerById(customerId, customer);
        
    }

	@DeleteMapping("/deleteCustomerById/{customerId}")
    public void deleteCustomerById(@PathVariable Integer customerId){
        customerService.deleteCustomerById(customerId);
        
    }

}
