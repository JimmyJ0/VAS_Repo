package de.leuphana.shop.component.structure;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.leuphana.shop.configuration.ArticleDeserializer;

@JsonDeserialize(using=ArticleDeserializer.class)
public abstract class Article {
	
	public Article() {
	}
	
	private long id;
	private String manufactor;
	private String name;
	private String price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	

}
