package de.leuphana.customer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerKafkaProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public CustomerKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendToTopic(String message) {
		kafkaTemplate.send("customer-topic", message);
		
	}

}
