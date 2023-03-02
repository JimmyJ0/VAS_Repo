package de.leuphana.customer.component.behaviour;

import org.springframework.beans.factory.annotation.Autowired;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;

public class CustomerService implements ICustomerService{
	
private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	public Customer saveCustomer(Customer customer) {
		System.out.println("... lege Kunde in Datenbank ab.");
		return customerRepository.save(customer);
	}


}
