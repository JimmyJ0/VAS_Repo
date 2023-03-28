package de.leuphana.shop.connector.kafka;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@Service
public class ShopKafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ShopKafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Article> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Map<String, String>> mapKafkaTemplate;

	public ShopKafkaProducer(KafkaTemplate<String, Article> kafkaTemplate,
			KafkaTemplate<String, Map<String, String>> mapKafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	    this.mapKafkaTemplate = mapKafkaTemplate;
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

	public void deleteArticle(String articleType, String id) {
		Map<String, String> articleData = new HashMap<>();
		articleData.put("articleType", articleType);
		articleData.put("id", id);
		mapKafkaTemplate.send("article_topic", articleData);
		
	}

}
