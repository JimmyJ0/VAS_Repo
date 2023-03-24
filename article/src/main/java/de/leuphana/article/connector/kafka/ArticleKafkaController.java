package de.leuphana.article.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ArticleKafkaController {
	
	private ArticleKafkaProducer articleKafkaProducer;
	
	@Autowired
	public ArticleKafkaController(ArticleKafkaProducer articleKafkaProducer) {
		this.articleKafkaProducer = articleKafkaProducer;
	}

	public void sendMessage(String message) {
		articleKafkaProducer.sendToTopic(message);
		
	}
	
}