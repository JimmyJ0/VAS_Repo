package de.leuphana.customer.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Address;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	private Customer customer;
	private Address address;

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	@BeforeEach
	public void setUp() {
		customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Naveen Vimalan");
		address = new Address();
		address.setAdressId(1);
		address.setCity("Lueneburg");
		address.setStreet("Soltauerstrasse 1");
		address.setZip(21335);
	}
	
	@Test
	void canCustomerBeCreated() {
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		Customer createdCustomer = customerService.createCustomer(customer);
		assertNotNull(createdCustomer);
		assertEquals(createdCustomer.getName(),customer.getName());
	}

	@Test
	void canCustomerBeFoundById() throws Exception {
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertNotNull(customerRepository.findById(customer.getCustomerId()));
	}

	@Test
	void canCustomerBeUpdated() throws Exception {
	    // Retrieve existing customer from database
	    when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
	    Address oldAddress = customer.getAddress();

	    // Update customer address
	    Address newAddress = new Address();
	    newAddress.setCity("Berlin");
	    newAddress.setStreet("Potsdamer Platz 1");
	    newAddress.setZip(10785);
	    customer.setAddress(newAddress);

	    // Call updateCustomerById method
	    when(customerRepository.save(any(Customer.class))).thenReturn(customer);
	    Customer updatedCustomer = customerService.updateCustomerById(customer.getCustomerId(), customer);

	    // Retrieve updated customer from database
	    Address updatedAddress = updatedCustomer.getAddress();

	    // Verify if customer details have been updated correctly in the database
	    assertEquals("Berlin", updatedAddress.getCity());
	    assertEquals("Potsdamer Platz 1", updatedAddress.getStreet());
	    assertEquals(10785, updatedAddress.getZip());
	    assertNotEquals(oldAddress, updatedAddress);
	}

	@Test
	void canAllCustomersBeFound() {
		List <Customer> allCustomers = customerService.getAllCustomers();
		assertNotNull(allCustomers);
	}

	@Test
	void canCustomerBeDeleted() throws Exception{
		//Create customer
		customerService.createCustomer(customer);

		// Delete the customer
	    customerService.deleteCustomer(customer.getCustomerId());

	    // Assert that the customer has been deleted
	    Integer customerId = customer.getCustomerId();
	    Customer deletedCustomer = customerRepository.findById(customerId).orElse(null);
	    assertNull(deletedCustomer);

	}

}

