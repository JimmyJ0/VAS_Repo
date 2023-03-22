package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.rest.ArticleRestConnectorRequester;
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
		book.setName("Sprechen Sie Delphi?");
		book.setAuthor("Mr. Gold");
		book.setManufactor("SnakeLand");
		book.setPrice(3.40);
		book.setBookCategory("Horror");
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
//	
//	@Test 
//	void canCdBeFound(){
//		Article cd = shopService.getArticleById("CD1");
//		assertNotNull(cd);
//	}
	
	@Test
	void canArticleBeUpdated() {
		shopService.getArticles();
		
		Book newBook = new Book();
		newBook.setArticleType("book");
		newBook.setName("Sprechen Sie Java 999");
		newBook.setPrice(3.30);
		shopService.updateArticle(newBook, 2l);
	}
	
	@Test 
	void canBookDeleted(){
		shopService.getArticles();
		assertTrue(shopService.deleteArticleById("BK1"));
	}


}
