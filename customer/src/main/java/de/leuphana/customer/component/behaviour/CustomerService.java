package de.leuphana.customer.component.behaviour;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;

@Service
public class CustomerService{

	@Autowired
    private CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;
	
	//CRUD operations
	public Customer createCustomer(Customer customer) {
		System.out.println("... create customer.");
		return customerSpringDataConnectorRequester.createCustomer(customer);
	}
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomers() {
		return customerSpringDataConnectorRequester.getAllCustomers();
	}
	@Transactional(readOnly = true)
	public Customer getCustomerById(Integer customerId) throws Exception {
		Optional<Customer> customerOptional = Optional.ofNullable(customerSpringDataConnectorRequester.getCustomerById(customerId));
		if (customerOptional.isPresent()) {
			System.out.println("... get customer with id " + customerId);
			return customerOptional.get();
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}
	public Customer updateCustomer(Integer customerId, Customer customer) throws Exception{
		Optional<Customer> customerOptional = Optional.ofNullable(customerSpringDataConnectorRequester.getCustomerById(customerId));
		if (customerOptional.isPresent()) {
			customer.setCustomerId(customerId);
			System.out.println("... update customer with id " + customerId);
			return customerSpringDataConnectorRequester.createCustomer(customer);
		} else {
			throw new Exception("... customer with id " + customerId + " not found.");
		}
	}
	public void deleteCustomer(Integer customerId) {
		System.out.println("... delete customer with id " + customerId);
		customerSpringDataConnectorRequester.deleteCustomerById(customerId);
	}

}
