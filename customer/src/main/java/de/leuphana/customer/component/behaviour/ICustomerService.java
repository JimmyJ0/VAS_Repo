package de.leuphana.customer.component.behaviour;

import java.util.List;

import de.leuphana.customer.component.structure.Customer;

public interface ICustomerService {
	Customer createCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Integer customerId);
	Customer updateCustomerById(Integer customerId, Customer customer);
	void deleteCustomerById(Integer customerId);

}
