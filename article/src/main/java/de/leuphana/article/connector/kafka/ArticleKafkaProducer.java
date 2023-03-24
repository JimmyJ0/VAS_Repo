package de.leuphana.article.connector.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArticleKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ArticleKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendToTopic(String message) {
		kafkaTemplate.send("article_database", message);
		
	}
}
