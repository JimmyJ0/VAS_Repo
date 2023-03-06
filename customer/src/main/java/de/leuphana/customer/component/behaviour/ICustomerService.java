package de.leuphana.customer.component.behaviour;

import java.util.List;

import de.leuphana.customer.component.structure.Customer;

public interface ICustomerService {
	
	Customer createCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Long id) throws Exception;
	Customer updateCustomer(Long id, Customer customer) throws Exception;
	void deleteCustomer(Long id);
	

}
