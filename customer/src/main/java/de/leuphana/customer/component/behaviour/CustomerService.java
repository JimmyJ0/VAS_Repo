package de.leuphana.customer.component.behaviour;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService implements ICustomerService{

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	//CRUD operations
	public Customer createCustomer(Customer customer) {
		try {
			LOGGER.info("Creating customer: {}", customer);
			Customer createdCustomer = customerRepository.save(customer);
			LOGGER.info("Customer created successfully: {}", createdCustomer);
			return createdCustomer;
		} catch (EntityNotFoundException e) {
			LOGGER.error("Error creating customer: {}", customer, e);
			throw e;
		}
	}

	public List<Customer> getAllCustomers() {
		try {
			LOGGER.info("Getting all customers");
			List<Customer> customers = customerRepository.findAll();
			LOGGER.info("Retrieved {} customers", customers.size());
			return customers;
		}catch (EntityNotFoundException e) {
			LOGGER.error("Error retrieving customer: {}", e);
			throw e;
		} 
	}

	public Customer getCustomerById(Integer customerId) throws Exception{
		LOGGER.info("Getting customer with id {}", customerId);
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new Exception("... customer with id " + customerId + " not found."));

	}


	public Customer updateCustomerById(Integer customerId, Customer updatedCustomer) throws Exception{
	    LOGGER.info("Updating customer with id {}", customerId);
	    
	    // Check if customer exists
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new Exception("Customer with id " + customerId + " not found"));

	    // Update customer details
	    customer.setName(updatedCustomer.getName());
	    customer.setAddress(updatedCustomer.getAddress());

	    // Save updated customer to database
	    Customer savedCustomer = customerRepository.save(customer);
	    LOGGER.info("Customer with id {} updated successfully", customerId);
	    return savedCustomer;
	}

	public void deleteCustomer(Integer customerId) {
		try {
			LOGGER.info("Deleting customer: {}", customerId);
			customerRepository.deleteById(customerId);
			LOGGER.info("Customer deleted successfully: {}", customerId);

		}catch(EntityNotFoundException e) {
			LOGGER.error("Error deleting customer {}", customerId, e);
			throw e;
		} 
	}

}
