package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomerSpringDataConnectorRequester {
	
	@Autowired
    private CustomerRepository customerRepository;
	
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
        		.orElseThrow(() -> new EntityNotFoundException("Customer not found with ID " + customerId));
    }

    public Customer updateCustomerById(Integer customerId, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID " + customerId));
        customer.setName(updatedCustomer.getName());
        customer.setAddress(updatedCustomer.getAddress());
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }
    
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
	
}
