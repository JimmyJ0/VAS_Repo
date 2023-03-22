package de.leuphana.shop.connector.kafka;

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

	public boolean sendArticle(Article article) {
		if (article != null) {
			LOG.info(String.format("Message sent -> %s", article.getName()));
			if (article instanceof Book) {
				kafkaTemplate.send("book_topic", article);
			} else if (article instanceof CD) {
				kafkaTemplate.send("cd_topic", article);
			}
			return true;
		}
		return false;
	}
	
	public boolean deleteArticle(Article article) {
		if(article != null) {
			kafkaTemplate.send("article_topic", article);
			return true;
		}
		return false;
	}

}
