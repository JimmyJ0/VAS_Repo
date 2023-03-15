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
	private BookCategory bookCategory;

	public Book() {
		super();
	}
	
	public void setArticleId(int articleId) {
		super.setArticleId(articleId);
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

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
//	@PrePersist
//	protected void onCreate() {
//		if (bookId == null) {
//			bookId = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
//		}
//	}
	

}