package de.leuphana.article.configuration;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
	
    @Query("SELECT COUNT(b) FROM Book b")
    long countBooks();
	
    @Query("SELECT COUNT(c) FROM CD c")
    long countCds();
	
	@Query("SELECT b FROM Book b")  
	List<Book> getAllBooks(Class<Book> c);
	
	@Query("SELECT c FROM CD c")  
	List<CD> getAllCds(Class<CD> c);

	@Query("SELECT a FROM Book a WHERE a.bookId = :id")
	Article getBookById(@Param("id") String id);
	
	@Query("SELECT a FROM CD a WHERE a.cdId = :id")
	Article getCdById(@Param("id") String id);

}
