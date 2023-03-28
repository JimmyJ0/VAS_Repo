package de.leuphana.article.component.structure;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Article{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long articleId;

	@Column(name = "article_type")
	private String articleType;

	@Column(name = "manufactor_name")
	private String manufactor;
	@Column(name = "article_name")
	private String name;
	@Column(name = "article_price")
	private double price;

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long id) {
		this.articleId = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
