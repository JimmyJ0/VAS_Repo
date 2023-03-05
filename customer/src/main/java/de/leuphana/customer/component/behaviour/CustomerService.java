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

	public Customer getCustomerById(Long id) throws Exception {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if (customerOptional.isPresent()) {
			System.out.println("... get customer with id " + id);
			return customerOptional.get();
		} else {
			throw new Exception("... customer with id " + id + " not found.");
		}
	}

	public Customer updateCustomer(Long id, Customer customer) throws Exception{
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if (customerOptional.isPresent()) {
			customer.setCustomerId(id);
			System.out.println("... update customer with id " + id);
			return customerRepository.save(customer);
		} else {
			throw new Exception("... customer with id " + id + " not found.");
		}
	}

	public void deleteCustomer(Long id) {
		System.out.println("... delete customer with id " + id);
		customerRepository.deleteById(id);
	}

}
