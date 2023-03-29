package de.leuphana.article.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.article.component.structure.Book;
import de.leuphana.article.component.structure.CD;
import de.leuphana.article.connector.rest.ArticleSpringDataConnectorRequester;

@SpringBootTest
class ArticleServiceTest {
	
	@Autowired
	ArticleSpringDataConnectorRequester restConnector;
	
	@Autowired
	private ArticleService articleService;
	
	
	@Test
	void canArticleDeleted() {
		assertTrue(articleService.deleteArticle("book", 1l));
		assertTrue(articleService.deleteArticle("cd", 1l));
	}
	
	@Test
	void canArticleInserted() {
		Book book = new Book();
		book.setArticleType("book");
		book.setAuthor("J.K. Rowling");
		book.setName("Harry Potter 42");
		book.setBookCategory("Horror");
		book.setManufactor("Book Comp.");
		book.setPrice(3.40);
		
		CD cd = new CD();
		cd.setName("cd");
		cd.setArtist("Helene Mischer");
		cd.setManufactor("Creepy Music");
		cd.setName("Ohrenkrebs Vol. 5");
		cd.setPrice(0.50);
		assertTrue(articleService.saveCD(cd));
		assertTrue(articleService.saveBook(book));
	}
	
	@Test
	void getArticles() {
		assertNotNull(articleService.getArticles());
	}
	
	@Test
	void getArticleById() {
		String articleType = "book";
		Long book = 1l;
		String articleType2 = "cd";
		Long cd = 1l;
		
		assertNotNull(articleService.getArticleById(articleType, book));
		assertNotNull(articleService.getArticleById(articleType2, cd));
	}

}
