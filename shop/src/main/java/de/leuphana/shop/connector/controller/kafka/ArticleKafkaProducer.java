package de.leuphana.shop.connector.controller.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@Service
public class ArticleKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Article> kafkaTemplate;
	
	public ArticleKafkaProducer(KafkaTemplate<String, Article> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendArticle(Article article) {
		LOG.info(String.format("Message sent -> %s", article.getName()));
		if(article instanceof Book) {
			kafkaTemplate.send("book_topic", article);
		}
		else if(article instanceof CD) {
			kafkaTemplate.send("cd_topic", article);
		}
		

	}
	
}
