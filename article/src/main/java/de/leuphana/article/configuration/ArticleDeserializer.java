package de.leuphana.article.configuration;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public class ArticleDeserializer implements Deserializer<Object> {
//public class ArticleDeserializer implements Deserializer<Article>{

	private static final Logger LOG = LoggerFactory.getLogger(ArticleDeserializer.class);

//    @Override
//    public Article deserialize(String topic, byte[] data) {
//        ObjectMapper mapper = new ObjectMapper();
//        Article article = null;
//        try {
//            String json = new String(data, "UTF-8");
//            if (topic.equals("book_topic")) {
//                article = mapper.readValue(json, Book.class);
//            } else if (topic.equals("cd_topic")) {
//                article = mapper.readValue(json, CD.class);
//            } 
//            else if(topic.equals("article_topic")) {
//            	article = mapper.readValue(json, Article.class);
//            }
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return article;
//    }
	@Override
	public Object deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			String json = new String(data, "UTF-8");
			switch (topic) {
			case "book_topic":
				object = (Book) mapper.readValue(json, Book.class);
				break;
			case "cd_topic":
				object = (CD) mapper.readValue(json, CD.class);
				break;
			case "article_topic": {
				JsonNode node = mapper.readTree(data);
				String articleType = node.get("articleType").asText();
				if(articleType.equals("book")) object = (Book) mapper.readValue(json, Book.class);
				else if(articleType.equals("cd")) object = (CD) mapper.readValue(json, CD.class);
				break;

			}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}
