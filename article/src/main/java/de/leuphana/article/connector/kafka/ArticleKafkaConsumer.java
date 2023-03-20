package de.leuphana.article.connector.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.Book;

@Service
public class ArticleKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleKafkaConsumer.class);

    @KafkaListener(topics = "book_topic", groupId = "article_group")
    public void receiveBook(Book book) {
        LOG.info(String.format("Received book message: %s", book.getName()));
        // Hier kannst du den empfangenen Buchartikel weiterverarbeiten
    }

}
