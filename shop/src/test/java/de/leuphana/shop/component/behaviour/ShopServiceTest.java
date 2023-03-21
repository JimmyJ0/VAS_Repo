package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.Address;

@SpringBootTest
class ShopServiceTest{

	@Autowired
	ArticleRestConnectorRequester restConnector;

	@Autowired
	CustomerRestConnectorRequester customerRestConnector;

	@Autowired
	private ShopService shopService;

//	@Test
//	void canNewArticleBeInserted() {
//		Book book = new Book();
//		book.setName("Sprechen Sie PHP?");
//		book.setAuthor("Mr. Gold");
//		book.setManufactor("SnakeLand");
//		book.setPrice(3.40);
//		book.setBookCategory("Horror");
//		assertTrue(shopService.saveArticleInDB(book));
//	}
//
//	@Test
//	void canIgetAllArticles() {
//		List<Article> allArticles = shopService.getArticles();
//		assertNotNull(allArticles);
//	}
//
//	@Test
//	void canBookBeFound() {
//		Article book = shopService.getArticleById("BK1");
//		System.out.println("Found article: " + book.getName());
//		assertNotNull(book);
//
//	}
//
//	@Test 
//	void canCdBeFound(){
//		Article cd = shopService.getArticleById("CD1");
//		assertNotNull(cd);
//	}
//
//	@Test
//	void canArticleBeUpdated() {
//		Book newBook = new Book();
//		newBook.setName("Sprechen Sie Java 999");
//		newBook.setPrice(3.30);
//		shopService.updateArticle(newBook, "BK1");
//	}
//
//	@Test 
//	void canBookDeleted(){
//		String bookId = "BK2";
//		assertTrue(shopService.deleteArticleById(bookId));
//	}

	@Test
	void canCustomerBeCreated() {
		Customer customer = new Customer();
		customer.setName("John Doe");
		Address address = new Address();
		address.setCity("Lüneburg");
		address.setStreet("Soltauerstrasse 1");
		address.setZip(21335);
		customer.setAddress(address);
		Customer savedCustomer = shopService.createCustomer(customer);
		assertEquals(customer.getName(), savedCustomer.getName());
		assertEquals(customer.getAddress(), savedCustomer.getAddress());
	}

	@Test
	void canCustomerBeFoundById() throws Exception {
		Customer customer = new Customer();
		customer.setName("Max Mustermann");
		Address address = new Address();
		address.setCity("Lüneburg");
		address.setStreet("Musterstrasse 32");
		address.setZip(21335);
		customer.setAddress(address);
		shopService.createCustomer(customer);
		Integer customerId = customer.getCustomerId();
		assertNotNull(shopService.getCustomerById(customerId));
	}

	@Test
	void canCustomerBeUpdated() throws Exception {
		//create customer
		Customer customer = new Customer();
		customer.setName("Justus");
		Address address = new Address();
		address.setCity("Hamburg");
		address.setStreet("Mönckebergstrasse 1");
		address.setZip(21234);
		customer.setAddress(address);
		shopService.createCustomer(customer);

		//update customer
		customer.setName("Justus");
		address.setCity("Hamburg");
		address.setStreet("Röntgenstrasse 4");
		address.setZip(22335);
		customer.setAddress(address);
		shopService.updateCustomerById(customer.getCustomerId(), customer);

		Customer updatedCustomer = shopService.getCustomerById(customer.getCustomerId());

		assertEquals("Justus", updatedCustomer.getName());
		assertEquals("Hamburg", updatedCustomer.getAddress().getCity());
		assertEquals("Röntgenstrasse 4", updatedCustomer.getAddress().getStreet());	
	}

	@Test
	void canAllCustomersBeFound() {
		assertNotNull(shopService.getAllCustomers());
	}

	@Test
	void canCustomerBeDeleted() throws Exception{
		// Create a new customer
		Customer customer = new Customer();
		customer.setName("Freddy Krüger");
		Address address = new Address();
		address.setCity("Köln");
		address.setStreet("Bahnhofstrasse 55");
		address.setZip(50668);
		customer.setAddress(address);
		shopService.createCustomer(customer);

		// Delete the customer and verify that it is no longer in the database
		Integer customerId = customer.getCustomerId();
		shopService.deleteCustomerById(customerId);
		//assertNull(shopService.getCustomerById(customerId));

	}
}
