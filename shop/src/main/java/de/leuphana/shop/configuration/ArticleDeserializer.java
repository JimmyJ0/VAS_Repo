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
		JsonNode typeNode = node.get("articleType");
		if (typeNode != null) {
			String type = node.get("articleType").asText();

			Article article = null;
			
			if (type.equals("cd")) {
				article = new CD();
				article.setArticleType(node.get("articleType").asText());
				Long id = node.get("articleId") == null ? null : node.get("articleId").asLong();
				if(id != null) article.setArticleId(id);
				article.setManufactor(node.get("manufactor").asText());
				((CD) article).setArtist(node.get("artist").asText());
				article.setName(node.get("name").asText());
				article.setPrice(node.get("price").asDouble());

				System.out.println("Deserizalied to an CD");
			}

			else if (type.equals("book")) {
				article = new Book();
				article.setArticleType(node.get("articleType").asText());
				Long id = node.get("articleId") == null ? null : node.get("articleId").asLong();
				if(id != null) article.setArticleId(id);
				article.setManufactor(node.get("manufactor").asText());
				((Book) article).setBookCategory(node.get("bookCategory").asText());
				article.setName(node.get("name").asText());
				article.setPrice(node.get("price").asDouble());
				((Book) article).setAuthor(node.get("author").asText());

			}
			return article;
		} 
		return null;

	}
}
