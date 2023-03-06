package de.leuphana.customer.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.configuration.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        //customer.setCustomerId(1);
        customer.setName("John Doe");
        customer.setAddress("Hamburg");

        Customer createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer.getCustomerId());
//
        Customer retrievedCustomer = customerRepository.findById(createdCustomer.getCustomerId()).orElse(null);
//
        assertNotNull(retrievedCustomer);
        assertEquals("John Doe", retrievedCustomer.getName());
        assertEquals("Hamburg", retrievedCustomer.getAddress());
    }
}
