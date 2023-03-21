package de.leuphana.customer.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Address;
import de.leuphana.customer.component.structure.Customer;


@SpringBootTest
public class CustomerServiceTest {
	
    @Autowired
    private CustomerService customerService;
    
    @Test
    void canCustomerBeCreated() {
    	Customer customer = new Customer();
    	customer.setName("John Doe");
    	Address address = new Address();
    	address.setCity("Lueneburg");
    	address.setStreet("Soltauerstrasse 1");
    	address.setZip(21335);
    	customer.setAddress(address);
    	Customer savedCustomer = customerService.createCustomer(customer);
    	assertEquals(customer.getName(), savedCustomer.getName());
    	assertEquals(customer.getAddress(), savedCustomer.getAddress());
    }
//    
//    @Test
//    void canCustomerBeFoundById() throws Exception {
//    	Customer customer = new Customer();
//    	customer.setName("Naveen Vimalan");
//    	Address address = new Address();
//    	address.setCity("Lueneburg");
//    	address.setStreet("Rotenbleicher Weg 32");
//    	address.setZip(21335);
//    	customer.setAddress(address);
//    	customerService.createCustomer(customer);
//    	Integer customerId = customer.getCustomerId();
//    	
//    	assertNotNull(customerService.getCustomerById(customerId));
//    	
//    }
////    
//    @Test
//    void canCustomerBeUpdated() throws Exception {
//    	//create customer
//    	Customer customer = new Customer();
//        customer.setName("Max");
//        Address address = new Address();
//        address.setCity("Lueneburg");
//        address.setStreet("Soltauerstrasse");
//        address.setZip(21335);
//        customer.setAddress(address);
//        customerService.createCustomer(customer);
//
//        //update customer
//        customer.setName("Maximilian");
//        address.setCity("Lueneburg");
//        address.setStreet("Rotes Feld 4");
//        address.setZip(21335);
//        customer.setAddress(address);
//        customerService.updateCustomerById(customer.getCustomerId(), customer);
//
//        Customer updatedCustomer = customerService.getCustomerById(customer.getCustomerId());
//
//        assertEquals("Maximilian", updatedCustomer.getName());
//        assertEquals("Rotes Feld 4", updatedCustomer.getAddress().getStreet());	
//    }
////    
//    @Test
//    void canAllCustomersBeFound() {
//    	assertNotNull(customerService.getAllCustomers());
//    }
////    
//    @Test
//    void canCustomerBeDeleted() throws Exception{
//    	// Create a new customer
//        Customer customer = new Customer();
//        customer.setName("Mustermann");
//        Address address = new Address();
//        address.setCity("Lueneburg");
//        address.setStreet("Soltauerstrasse");
//        address.setZip(21335);
//        customer.setAddress(address);
//        customerService.createCustomer(customer);
//
//        // Delete the customer and verify that it is no longer in the database
//        Integer customerId = customer.getCustomerId();
//        customerService.deleteCustomerById(customerId);
//        //assertNull(customerService.getCustomerById(customerId));
//    	  
//    }
    
}

