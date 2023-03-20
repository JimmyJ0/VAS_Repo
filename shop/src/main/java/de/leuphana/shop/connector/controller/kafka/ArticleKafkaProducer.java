package de.leuphana.shop.connector.controller.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;

@Service
public class ArticleKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;
	
	public ArticleKafkaProducer(KafkaTemplate<String, Book> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendArticle(Book articleData) {
		LOG.info(String.format("Message sent -> %s", articleData.getName()));
		
		Message<Book> message = MessageBuilder.withPayload(articleData).setHeader(KafkaHeaders.TOPIC, "book_topic").build();
		kafkaTemplate.send("book_topic", articleData);
	}
	
}
