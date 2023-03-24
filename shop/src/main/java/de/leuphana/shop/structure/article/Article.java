package de.leuphana.shop.structure.article;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.leuphana.shop.configuration.ArticleDeserializer;

@JsonDeserialize(using=ArticleDeserializer.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CD.class, name = "cd"),
    @JsonSubTypes.Type(value = Book.class, name = "book")
})
public abstract class Article {
	
	public Article() {
	}
	
	private String articleType;
	private String manufactor;
	private String name;
	private double price;
	
	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

}
