package de.leuphana.article.component.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book extends Article {
	
	private String author;
	private String bookCategory;
	
	public Book() {
		super();

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	
}