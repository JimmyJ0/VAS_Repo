package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.shop.component.structure.article.Article;
import de.leuphana.shop.component.structure.article.Book;
import de.leuphana.shop.connector.ArticleRestConnector;

class ShopServiceTest {

	private static ShopService shopService;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ArticleRestConnector arc = new ArticleRestConnector();
		shopService = new ShopService(arc);
	}

	@Test
	void canNewArticleBeInserted() {
		Book newBook = new Book();
		newBook.setName("Sprechen Sie Java?");
		newBook.setAuthor("Luci Lucifer");
		newBook.setManufactor("Hell Factory");
		newBook.setPrice("2.30");
		assertTrue(shopService.insertArticle(newBook));
	}
	
	@Test
	void canIgetAllArticles() {
		List<Article> allArticles = shopService.getArticles();
		assertNotNull(allArticles);
	}

}
