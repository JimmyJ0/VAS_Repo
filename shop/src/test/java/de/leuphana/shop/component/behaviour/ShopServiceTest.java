package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;

@SpringBootTest
class ShopServiceTest{

	@Autowired
	ArticleRestConnectorRequester restConnector;
	
	@Autowired
	private ShopService shopService;
	
	@Test
	void canNewArticleBeInserted() {
		Book book = new Book();
		book.setName("Sprechen Sie Python?");
		book.setAuthor("Mr. Gold");
		book.setManufactor("SnakeLand");
		book.setPrice(3.40);
		book.setBookCategory("Horror");
		assertTrue(shopService.saveArticleInDB(book));
	}
	
	@Test
	void canIgetAllArticles() {
		List<Article> allArticles = shopService.getArticles();
		assertNotNull(allArticles);
	}
	
	@Test
	void canBookBeFound() {
		Article book = shopService.getArticleById("BK1");
		System.out.println("Found article: " + book.getName());
		assertNotNull(book);
		
	}
	
	@Test 
	void canCdBeFound(){
		Article cd = shopService.getArticleById("CD1");
		assertNotNull(cd);
	}
	
	@Test
	void canArticleBeUpdated() {
		Book newBook = new Book();
		newBook.setName("Sprechen Sie Java 999");
		newBook.setPrice(3.30);
		shopService.updateArticle(newBook, "BK1");
	}
	
	@Test 
	void canBookDeleted(){
		String bookId = "BK6";
		assertTrue(shopService.deleteArticleById(bookId));
	}
	
	@Test 
	void pingShit(){
		shopService.ping();
	}
	

}
