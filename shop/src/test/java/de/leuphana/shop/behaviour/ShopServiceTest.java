package de.leuphana.shop.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;

import de.leuphana.shop.structure.article.Article;

@TestMethodOrder(OrderAnnotation.class)
public class ShopServiceTest {
	private static ShopService shop;
	private static Integer customerId;
	private static de.leuphana.shop.structure.sales.Order order;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		shop = new ShopService();
		customerId = shop.createCustomerWithCart();
		for (Article article : shop.getCatalog().getArticles()) {
			shop.addArticleToCart(customerId, article.getArticleId());
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Order(1)
	@Test
	void canOrderBeCreatedTest() {
		order = shop.checkOutCart(customerId);
		Assertions.assertNotNull(order);
	}
	
	@Order(2)
	@Test
	void canInvoiceBeCreatedTest() {
		Assertions.assertNotNull(shop.createInvoice(order));
	}

}
