package de.leuphana.customer.component.behaviour;

import java.util.List;

import de.leuphana.customer.component.structure.Customer;

public interface ICustomerService {
	public Customer createCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(Integer customerId) throws Exception;
	public Customer updateCustomerById(Integer customerId, Customer customer) throws Exception;
	public void deleteCustomer(Integer customerId);

}
