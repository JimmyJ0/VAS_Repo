package de.leuphana.article.configuration;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	@Query("SELECT b FROM Book b")  
	List<Book> getAllBooks(Class<Book> c);
	
	@Query("SELECT c FROM CD c")  
	List<CD> getAllCds(Class<CD> c);
}
