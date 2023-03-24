package de.leuphana.customer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic customerSendTopic() {
		return TopicBuilder.name("customer-send-topic").build();
				
	}
	
	@Bean
	public NewTopic customerDeleteTopic() {
		return TopicBuilder.name("customer-delete-topic").build();
				
	}

}
