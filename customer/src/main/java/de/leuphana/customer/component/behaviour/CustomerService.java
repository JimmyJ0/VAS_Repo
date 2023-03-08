package de.leuphana.customer.component.behaviour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;

@Service
public class CustomerService{

	@Autowired
    private CustomerSpringDataConnectorRequester customerConnectorRequester;

	//CRUD operations
	@Transactional(readOnly = true) //stellt sicher dass in der DB nichts modifiziert werden kann
	public Customer createCustomer(Customer customer) {
		System.out.println("... create customer.");
		return customerConnectorRequester.createCustomer(customer);
	}
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return customerConnectorRequester.getAllCustomers();
	}
	@Transactional(readOnly = true)
	public Customer getCustomerById(Integer customerId) throws Exception {
		Optional<Customer> customerOptional = Optional.of(customerConnectorRequester.getCustomerById(customerId));
		if (customerOptional.isPresent()) {
			System.out.println("... get customer with id " + customerId);
			return customerOptional.get();
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}
	@Transactional(readOnly = true)
	public Customer updateCustomer(Integer customerId, Customer customer) throws Exception{
		Optional<Customer> customerOptional = Optional.of(customerConnectorRequester.getCustomerById(customerId));
		if (customerOptional.isPresent()) {
			customer.setCustomerId(customerId);
			System.out.println("... update customer with id " + customerId);
			return customerConnectorRequester.createCustomer(customer);
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}
	@Transactional(readOnly = true)
	public void deleteCustomer(Integer customerId) {
		System.out.println("... delete customer with id " + customerId);
		customerConnectorRequester.deleteCustomerById(customerId);
	}

}
