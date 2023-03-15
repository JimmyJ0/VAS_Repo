package de.leuphana.shop.configuration;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;


public class ArticleDeserializer extends StdDeserializer<Article> {

	public ArticleDeserializer() {
		super(Article.class);
	}

	@Override
	public Article deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode node = p.getCodec().readTree(p);

		JsonNode typeNode = node.get("type");
		// POST-Mapping
		if (typeNode != null) {
			String type = node.get("type").asText();

			if (type.equals("cd")) {
				CD cd = new CD();

				cd.setManufactor(node.get("manufactor").asText());
				cd.setArtist(node.get("artist").asText());
				cd.setName(node.get("name").asText());
				cd.setPrice(node.get("price").asDouble());

				System.out.println("Deserizalied to an CD");

				return cd;
			}

			else if (type.equals("book")) {
				Book book = new Book();

				book.setManufactor(node.get("manufactor").asText());
				book.setBookCategory(node.get("bookCategory").asText());
				book.setName(node.get("name").asText());
				book.setPrice(node.get("price").asDouble());
				book.setAuthor(node.get("author").asText());

				return book;

			}
			// TODO: ExceptionHandling
		} 
		// GET-Mapping
		else {
			// Hübsch machen! Krampf hier...
			System.out.println("SHITFUCK");
			JsonNode book2 = node.get("bookId");
			JsonNode cd2 = node.get("cdId");
			System.out.println(book2);
			System.out.println(cd2);
			
			if(book2 != null) {
				Book book = new Book();
				book.setId(node.get("id").asLong());
				book.setBookId(node.get("bookId").asText());
				book.setManufactor(node.get("manufactor").asText());
				book.setBookCategory(node.get("bookCategory").asText());
				book.setName(node.get("name").asText());
				book.setPrice(node.get("price").asDouble());
				book.setAuthor(node.get("author").asText());
				
				return book;
			}
			else if(cd2.asText() != null) {
				CD cd = new CD();
				cd.setCdId(node.get("cdId").asText());
				cd.setManufactor(node.get("manufactor").asText());
				cd.setArtist(node.get("artist").asText());
				cd.setName(node.get("name").asText());
				cd.setPrice(node.get("price").asDouble());
				
				return cd;
			}
			
		}
		return null;

	}
}
