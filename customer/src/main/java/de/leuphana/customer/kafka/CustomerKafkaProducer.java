package de.leuphana.customer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.structure.Customer;

@Service
public class CustomerKafkaProducer {
    
    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendCustomer(Customer customer) {
        kafkaTemplate.send("customer_topic_create", customer);
    }
    
    public void deleteCustomer(Integer customerId) {
        kafkaTemplate.send("customer_topic_delete", customerId.toString(), null);
    }
}
