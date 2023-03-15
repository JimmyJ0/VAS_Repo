<<<<<<< HEAD:shop/src/main/java/de/leuphana/shop/structure/article/Book.java
package de.leuphana.shop.structure.article;
=======
package de.leuphana.shop.component.structure.article;
>>>>>>> development_jimmy:shop/src/main/java/de/leuphana/shop/structure/article/article/Book.java

public class Book extends Article {
	
	private Long id;
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



	public void setBookId(String bookId) {
		this.bookId = bookId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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
