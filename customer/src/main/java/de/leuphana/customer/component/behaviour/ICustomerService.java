package de.leuphana.customer.component.behaviour;

import java.util.List;

import de.leuphana.customer.component.structure.Customer;

public interface ICustomerService {
	
	Customer createCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Integer customerId) throws Exception;
	Customer updateCustomer(Integer customerId, Customer customer) throws Exception;
	void deleteCustomer(Integer customerId);
	

}
