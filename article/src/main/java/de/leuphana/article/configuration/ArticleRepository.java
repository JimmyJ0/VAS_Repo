package de.leuphana.article.configuration;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;

@Transactional
public interface ArticleRepository extends JpaRepository<Article, String>{
	
    @Query("SELECT COUNT(b) FROM Book b")
    long countBooks();
	
    @Query("SELECT COUNT(c) FROM CD c")
    long countCds();
	
	@Query("SELECT b FROM Book b")  
	List<Book> getAllBooks(Class<Book> c);
	
	@Query("SELECT c FROM CD c")  
	List<CD> getAllCds(Class<CD> c);

	@Query("SELECT a FROM Book a WHERE a.id = :id")
	Article getBookById(@Param("id") Long id);
	
	@Query("SELECT a FROM CD a WHERE a.id = :id")
	Article getCdById(@Param("id") Long id);
	
//	 SPRING-BUG (Bekannt) Müssten downgraden, ansonsten alternative delete-method:
//    @Modifying
//    @Query(value = "DELETE FROM books WHERE id = ?1", nativeQuery = true)
//    void deleteBookById(@Param("id") String id);
//	
	
// DELETE METHOD (By normal id)
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = :bookId")
    void deleteBookById(@Param("bookId") long bookId);
    
    @Modifying
    @Query("DELETE FROM CD c WHERE c.id = :cdId")
    void deleteCdById(@Param("cdId") long cdId);

}
