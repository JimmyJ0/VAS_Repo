package de.leuphana.article.component.structure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book extends Article {
	
	@Column(name = "book_id")
	private String bookId;
	private String author;
	private String bookCategory;
	
	public Book() {
		super();

	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String id) {
		this.bookId = id;
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
	
//	@PrePersist
//	protected void onCreate() {
//		if (bookId == null) {
//			bookId = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
//		}
//	}
	

}