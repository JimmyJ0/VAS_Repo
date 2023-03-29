package de.leuphana.article.configuration;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public class ArticleDeserializer implements Deserializer<Object> {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleDeserializer.class);

	@Override
	public Object deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			String json = new String(data, "UTF-8");
			switch (topic) {
			case "book_topic":
				object = mapper.readValue(json, Book.class);
				break;
			case "cd_topic":
				object = mapper.readValue(json, CD.class);
				break;
			case "article_topic": {
				object = mapper.readValue(data, new TypeReference<Map<String, String>>() {});
				break;
				
			}
			default:
				LOG.info("Fallback - Topic not available");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}
