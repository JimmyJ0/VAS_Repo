package de.leuphana.article.connector.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

@Service
public class ArticleKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaConsumer.class);

    @KafkaListener(topics = {"book_topic", "cd_topic"}, groupId = "article_group")
    public void receiveBook(Article article) {
    	if(article instanceof Book) {
        	LOG.info(article.getClass().toString());
            LOG.info(String.format("Received book message: %s", ((Book) article).getAuthor()));
    	}
    	else if(article instanceof CD) {
        	LOG.info(article.getClass().toString());
            LOG.info(String.format("Received cd message: %s", ((CD) article).getArtist()));
    	}
    	

    }
}
