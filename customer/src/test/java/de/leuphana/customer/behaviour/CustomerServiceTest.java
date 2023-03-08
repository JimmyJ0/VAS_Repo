package de.leuphana.customer.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception{
    	// create a new cart
        Cart cart = new Cart();

        // create a new customer with the cart
        Customer customer = new Customer(cart);
        customer.setName("John Doe");
        customer.setAddress("123 Main Street");

        // add an order for the customer
        Order order = new Order();
        order.setOrderId(1);
        customer.addOrder(order);

        Customer createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer.getCustomerId());

        Customer retrievedCustomer = customerRepository.findById(createdCustomer.getCustomerId()).orElse(null);

        assertNotNull(retrievedCustomer);
        assertEquals("John Doe", retrievedCustomer.getName());
        assertEquals("123 Main Street", retrievedCustomer.getAddress());
    }
    @Test
    void canCustomerBeFound() {
    	
        
    }
}
