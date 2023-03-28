package de.leuphana.shop.connector.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.sales.Customer;

@Service
public class CustomerKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;

	public boolean sendCustomer(Customer customer) {
		if(customer!=null) {
			LOG.info("Message sent -> {}", customer);
			kafkaTemplate.send("customer_topic_create", customer);
			return true;
		}
		return false; 
	}

	public boolean deleteCustomer(Integer customerId) {
	    if (customerId != null) {
	        LOG.info("Message sent -> {}", customerId);
	        kafkaTemplate.send("customer_topic_delete", customerId.toString(), null);
	        return true;
	    }
	    return false;
	}

}
