package de.leuphana.shop.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.sales.Customer;


@Service
public class CustomerProducer {

    private static final Logger logger = LoggerFactory.getLogger(CustomerProducer.class);

    @Autowired
    private KafkaTemplate<Integer, Customer> kafkaTemplate;

    public void sendCustomer(Customer customer) {
        
        Message<Customer> message = MessageBuilder
        		.withPayload(customer)
        		.setHeader(KafkaHeaders.TOPIC, "customer-send-topic")
        		.build();

        try {
        	logger.info("Sending customer to Kafka: {}", customer);
            kafkaTemplate.send(message);
            logger.info("Sent customer to Kafka: {}", customer);
        } catch (KafkaException e) {
            logger.error("Error sending customer to Kafka", e);
        }
    }

    public void deleteCustomer(Customer customer) {
    	
    	Message<Customer> message = MessageBuilder
        		.withPayload(customer)
        		.setHeader(KafkaHeaders.TOPIC, "customer-delete-topic")
        		.build();
        
    	logger.info("Sending customer deletion event to Kafka for customerId: {}", customer.getCustomerId());

        try {
        	logger.info("Sending customer deletion event to Kafka: {}", customer);
            kafkaTemplate.send(message);
            logger.info("Sent customer deletion event to Kafka for customerId: {}", customer.getCustomerId());
        } catch (KafkaException e) {
            logger.error("Error sending customer deletion event to Kafka", e);
        }
    }

}
