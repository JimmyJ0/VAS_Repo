package de.leuphana.customer.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CustomerServiceTest {
	
    @Autowired
    private CustomerService customerService;
    
//  @BeforeAll
//  void setUp() throws Exception{
//  }
    
    @Test
    void canCustomerBeCreated() {
    	Customer customer = new Customer();
    	customer.setName("John Doe");
    	customer.setAddress("Musterstrasse 123");
    	Customer savedCustomer = customerService.createCustomer(customer);
    	assertEquals(customer.getName(), savedCustomer.getName());
    	assertEquals(customer.getAddress(), savedCustomer.getAddress());
    }
//    
    @Test
    void canCustomerBeFoundById() throws Exception {
    	Customer customer = new Customer();
    	customer.setName("Naveen Vimalan");
    	customer.setAddress("Rotenbleicher Weg 32");
    	customerService.createCustomer(customer);
    	Integer customerId = customer.getCustomerId();
    	
    	assertNotNull(customerService.getCustomerById(customerId));
    	
    }
//    
    @Test
    void canCustomerBeUpdated() throws Exception {
    	Customer customer = new Customer();
    	customer.setName("Max");
    	customer.setAddress("Universitätsallee");
    	customerService.createCustomer(customer);
    	
    	customer.setName("Maximilian");
    	customer.setAddress("Universitätsallee 123");
    	customerService.updateCustomer(customer.getCustomerId(),customer);
    	
    	Customer updatedCustomer = customerService.getCustomerById(customer.getCustomerId());
    	
    	assertEquals("Maximilian",updatedCustomer.getName());
        assertEquals("Universitätsallee 123", updatedCustomer.getAddress());	
    	
    }
//    
    @Test
    void canAllCustomersBeFound() {
    	assertNotNull(customerService.getAllCustomers());
    }
//    
    @Test
    void canCustomerBeDeleted() throws Exception{
    	Customer customer = new Customer();
    	customer.setName("Mustermann");
    	customer.setAddress("Rotes Feld");
    	customerService.createCustomer(customer);	
    	Integer customerId = customer.getCustomerId();

    	Optional<Customer> customerOptional = Optional.of(customerService.getCustomerById(customerId));
        if (customerOptional.isPresent()) {
        	customerService.deleteCustomer(customerId);
        } else {
        	assertNull(customerService.getCustomerById(customerId));
        }
    	  
    }
    
}
