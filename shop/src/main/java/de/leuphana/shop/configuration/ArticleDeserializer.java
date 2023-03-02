package de.leuphana.shop.configuration;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.leuphana.shop.component.structure.Article;
import de.leuphana.shop.component.structure.Book;
import de.leuphana.shop.component.structure.CD;

public class ArticleDeserializer extends StdDeserializer<Article> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArticleDeserializer() {
		super(Article.class);
	}

	@Override
	public Article deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode node = p.getCodec().readTree(p);
		String type = node.get("type").asText();

		System.out.println("ASDASD" + node.toPrettyString());

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
		return null;

	}

}
