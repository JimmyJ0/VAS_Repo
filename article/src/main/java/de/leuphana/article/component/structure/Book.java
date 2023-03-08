package de.leuphana.article.component.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book extends Article {
	
	private String author;
	private BookCategory bookCategory;

	public Book() {
		super();
	}
	
	public void setArticleId(int articleId) {
		super.setArticleId(articleId);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

}
