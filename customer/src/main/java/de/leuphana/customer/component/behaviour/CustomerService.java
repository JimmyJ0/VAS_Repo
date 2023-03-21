package de.leuphana.customer.component.behaviour;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;

@Service
public class CustomerService{

	private static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	 
	@Autowired
	private CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;

	//CRUD operations
	@Transactional
	public Customer createCustomer(Customer customer) {
		try {
			LOGGER.info("Creating customer: {}", customer);
			Customer createdCustomer = customerSpringDataConnectorRequester.createCustomer(customer);
			LOGGER.info("Customer created successfully: {}", createdCustomer);
			return createdCustomer;
		} catch (Exception e) {
			LOGGER.error("Error creating customer: {}", customer, e);
			throw e;
		}
	}
	@Transactional
	public List<Customer> getAllCustomers() {
		try {
			LOGGER.info("Getting all customers");
			List<Customer> customers = customerSpringDataConnectorRequester.getAllCustomers();
			LOGGER.info("Retrieved {} customers", customers.size());
			return customers;
		}catch (Exception e) {
			LOGGER.error("Error retrieving customer: {}", e);
			throw e;
		} 
	}
	@Transactional
	public Customer getCustomerById(Integer customerId) throws Exception {
		Optional<Customer> customerOptional = Optional.ofNullable(customerSpringDataConnectorRequester.getCustomerById(customerId));
		try {
			LOGGER.info("Getting customer with id {}", customerId);
			return customerOptional.get();

		}catch(Exception e) {
			LOGGER.error("Error retrieving customer with id {}", customerId, e);
			throw e;
		} 

	}

	@Transactional
	public Customer updateCustomerById(Integer customerId, Customer customer) throws Exception{
		try {
			LOGGER.info("Updating customer with id {}", customerId);
			customer.setCustomerId(customerId);
			customer.setName(customer.getName());
			customer.setAddress(customer.getAddress());
			return customerSpringDataConnectorRequester.createCustomer(customer);
		}catch(Exception e) {
			LOGGER.error("Error updating customer with id {}", customerId, e);
			throw e;
		} 

	}

	@Transactional
	public void deleteCustomerById(Integer customerId) {
		try {
			LOGGER.info("Deleting customer with id {}", customerId);
			customerSpringDataConnectorRequester.deleteCustomerById(customerId);
			LOGGER.info("Customer with id {}", customerId, "deleted successfully");

		}catch(Exception e) {
			LOGGER.error("Error deleting customer with id {}", customerId, e);
			throw e;
		} 
	}

}
