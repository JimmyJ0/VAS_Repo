package de.leuphana.customer.kafka;

import org.springframework.stereotype.Component;

@Component
public class CustomerKafkaController {
	
	private CustomerKafkaProducer customerKafkaProducer;

	public CustomerKafkaController(CustomerKafkaProducer customerKafkaProducer) {
		super();
		this.customerKafkaProducer = customerKafkaProducer;
	}
	
	public void sendMessage(String message) {
		customerKafkaProducer.sendToTopic(message);
		
	}

}
