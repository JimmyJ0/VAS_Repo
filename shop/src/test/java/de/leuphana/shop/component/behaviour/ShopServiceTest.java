package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;


class ShopServiceTest {

	private static ShopService shopService;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ArticleRestConnectorRequester arc = new ArticleRestConnectorRequester();
		shopService = new ShopService(arc);
	}

	@Test
	void canNewArticleBeInserted() {
		Book book = new Book();
		book.setName("Sprechen Sie Java 2?");
		book.setAuthor("Luci Lucifer");
		book.setManufactor("Hell Factory");
		book.setPrice(2.30);
		book.setBookCategory("Fantasy");
		assertTrue(shopService.saveArticle(book));
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
		newBook.setName("Sprechen Sie Java 33");
		newBook.setPrice(3.30);
		shopService.updateArticle(newBook, "BK1");
	}

}
