package de.leuphana.shop.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic bookJson() {
		return TopicBuilder.name("book_topic").build();
	}
	
	@Bean
	public NewTopic cdJson() {
		return TopicBuilder.name("cd_topic").build();
	}
	
	
	
}
