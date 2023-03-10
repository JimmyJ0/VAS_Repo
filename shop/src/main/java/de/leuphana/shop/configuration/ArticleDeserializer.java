package de.leuphana.shop.configuration;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.leuphana.shop.component.structure.article.Article;
import de.leuphana.shop.component.structure.article.Book;
import de.leuphana.shop.component.structure.article.CD;

public class ArticleDeserializer extends StdDeserializer<Article> {

	public ArticleDeserializer() {
		super(Article.class);
	}

	@Override
	public Article deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode node = p.getCodec().readTree(p);

		System.out.println("ASDASD" + node.toPrettyString());

		JsonNode typeNode = node.get("type");
		if (typeNode != null) {
			String type = node.get("type").asText();

			if (type.equals("cd")) {
				CD cd = new CD();

				cd.setManufactor(node.get("manufactor").asText());
				cd.setArtist(node.get("artist").asText());
				cd.setName(node.get("name").asText());
				cd.setPrice(node.get("price").asText());

				System.out.println("Deserizalied to an CD");

				return cd;
			}

			else if (type.equals("book")) {
				Book book = new Book();

				book.setManufactor(node.get("manufactor").asText());
				book.setBookCategory(node.get("bookCategory").asText());
				book.setName(node.get("name").asText());
				book.setPrice(node.get("price").asText());
				book.setAuthor(node.get("author").asText());

				return book;

			}
			// TODO: ExceptionHandling
		} else {
			
			if(node.get("id").asText().contains("BK")) {
				Book book = new Book();
				book.setManufactor(node.get("manufactor").asText());
				book.setBookCategory(node.get("bookCategory").asText());
				book.setName(node.get("name").asText());
				book.setPrice(node.get("price").asText());
				book.setAuthor(node.get("author").asText());
				
				return book;
			}
			else if(node.get("id").asText().contains("CD")) {
				CD cd = new CD();
				cd.setManufactor(node.get("manufactor").asText());
				cd.setArtist(node.get("artist").asText());
				cd.setName(node.get("name").asText());
				cd.setPrice(node.get("price").asText());
				
				return cd;
			}
			
		}
		return null;

	}

}
