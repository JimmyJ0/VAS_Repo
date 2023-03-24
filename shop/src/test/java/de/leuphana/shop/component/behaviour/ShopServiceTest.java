package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.rest.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;

@SpringBootTest
class ShopServiceTest{

	@Autowired
	ArticleRestConnectorRequester restConnector;
	
	@Autowired
	private ShopService shopService;
	
	@Test
	void canNewArticleBeInserted() {
		Book book = new Book();
		book.setArticleType("book");
		book.setName("Sprechen Sie Delphi?");
		book.setAuthor("Mr. Gold");
		book.setManufactor("SnakeLand");
		book.setPrice(3.40);
		book.setBookCategory("Horror");
		
		CD cd = new CD();
		cd.setArticleType("cd");
		cd.setName("Fire Album");
		cd.setArtist("Dj Fire");
		cd.setManufactor("Dony");
		cd.setPrice(4334.4);
		assertTrue(shopService.saveArticleInDB(cd));
		assertTrue(shopService.saveArticleInDB(book));
	}
	
	@Test
	void canIgetAllArticles() {
		Map<String, Article> allArticles = shopService.getArticles();
		assertNotNull(allArticles);
	}
	
	@Test
	void canBookBeFound() {
		Article book = shopService.getArticleByIdFromDB("book", 1l);
		System.out.println("Found article: " + book.getName());
		assertNotNull(book);	
	}
	
	@Test
	void canArticleBeUpdated() {
		shopService.getArticles();
		canNewArticleBeInserted();
		Book newBook = new Book();
		newBook.setArticleType("book");
		newBook.setName("Sprechen Sie Java 999");
		newBook.setPrice(3.30);
		shopService.updateArticle(newBook, 1l);
	}
	
	
	@Test 
	void canBookDeleted(){
		shopService.getArticles();
		assertTrue(shopService.deleteArticleById("BK2"));
	}
}
