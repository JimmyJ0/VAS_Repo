package de.leuphana.article.configuration;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public class ArticleDeserializer implements Deserializer<Article>{
	

    @Override
    public Article deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Article article = null;
        try {
            String json = new String(data, "UTF-8");
            if (topic.equals("book_topic")) {
                article = mapper.readValue(json, Book.class);
            } else if (topic.equals("cd_topic")) {
                article = mapper.readValue(json, CD.class);
            } 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return article;
    }
}



