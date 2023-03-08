package de.leuphana.customer.component.behaviour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;

@Service
public class CustomerService implements ICustomerService{

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	//CRUD operations
	@Override
	public Customer createCustomer(Customer customer) {
		System.out.println("... create customer.");
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Integer customerId) throws Exception {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (customerOptional.isPresent()) {
			System.out.println("... get customer with id " + customerId);
			return customerOptional.get();
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}

	public Customer updateCustomer(Integer customerId, Customer customer) throws Exception{
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (customerOptional.isPresent()) {
			customer.setCustomerId(customerId);
			System.out.println("... update customer with id " + customerId);
			return customerRepository.save(customer);
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}

	public void deleteCustomer(Integer customerId) {
		System.out.println("... delete customer with id " + customerId);
		customerRepository.deleteById(customerId);
	}

}
