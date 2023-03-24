package de.leuphana.customer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Customer;

@Service
public class ShopKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopKafkaConsumer.class);
	
	private CustomerService customerService;


    public ShopKafkaConsumer(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@KafkaListener(topics = "customer-send-topic", groupId = "customer-group")
    public void receiveCustomer(Customer customer) {
        try {
            LOGGER.info("Received customer: {}", customer);
            customerService.createCustomer(customer);
            LOGGER.info("Created customer successfully: {}", customer);
        } catch (KafkaException e) {
            LOGGER.error("Error processing customer: {}", customer, e);
        }
    }

    @KafkaListener(topics = "customer-delete-topic", groupId = "customer-group")
    public void deleteCustomer(Integer customerId) {
        try {
        	LOGGER.info("Deleting customer with ID: {}", customerId);
            customerService.deleteCustomerById(customerId);
            LOGGER.info("Deleted customer successfully with ID: {}", customerId);
        } catch (KafkaException e) {
        	LOGGER.error("Error deleting customer with ID: {}", customerId, e);
        }
    }
}
