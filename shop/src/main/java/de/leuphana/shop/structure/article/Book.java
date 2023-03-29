package de.leuphana.shop.structure.article;

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
