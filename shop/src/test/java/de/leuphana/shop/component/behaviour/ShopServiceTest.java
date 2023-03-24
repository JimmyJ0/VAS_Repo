package de.leuphana.shop.component.behaviour;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.kafka.CustomerProducer;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.sales.Address;
import de.leuphana.shop.structure.sales.Customer;

@SpringBootTest
class ShopServiceTest{

	@Autowired
	ArticleRestConnectorRequester restConnector;

	@Autowired
	CustomerRestConnectorRequester customerRestConnector;

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private CustomerProducer customerProducer;

	@Test
	void canNewArticleBeInserted() {
		Book book = new Book();
		book.setName("Sprechen Sie PHP?");
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
		String bookId = "BK2";
		assertTrue(shopService.deleteArticleById(bookId));
	}

	@Test
	void canCustomerBeCreated() throws InterruptedException {
		// Create a customer object
	    Customer customer = new Customer();
	    customer.setCustomerId(1);
	    customer.setName("Max Mustermann");
	    Address address = new Address();
	    address.setAdressId(1);
	    address.setCity("Lueneburg");
	    address.setStreet("Soltauerstrasse 1");
	    address.setZip(21335);
	    customer.setAddress(address);
	    
	    customerProducer.sendCustomer(customer);

	}

//	@Test
//	void canCustomerBeFoundById() throws Exception {
//		Customer customer = new Customer();
//	    customer.setName("Max Mustermann");
//	    Address address = new Address();
//	    address.setCity("Lüneburg");
//	    address.setStreet("Musterstrasse 32");
//	    address.setZip(21335);
//	    customer.setAddress(address);
//
//	    // create the customer
//	    Customer savedCustomer = shopService.createCustomer(customer);
//	    Integer customerId = savedCustomer.getCustomerId();
//
//	    // make sure the customer was successfully created
//	    assertNotNull(customerId);
//
//	    // retrieve the customer by id
//	    Customer foundCustomer = shopService.getCustomerById(customerId);
//
//	    // make sure the correct customer was retrieved
//	    assertEquals(customer.getName(), foundCustomer.getName());
//	    assertEquals(customer.getAddress(), foundCustomer.getAddress());
//	}
//
//	@Test
//	void canCustomerBeUpdated() throws Exception {
//	    //create customer
//	    Customer customer = new Customer();
//	    customer.setName("Justus");
//	    Address address = new Address();
//	    address.setCity("Hamburg");
//	    address.setStreet("Mönckebergstrasse 1");
//	    address.setZip(21234);
//	    customer.setAddress(address);
//	    Customer createdCustomer = shopService.createCustomer(customer);
//
//	    //update customer
//	    Customer updatedCustomer = shopService.getCustomerById(createdCustomer.getCustomerId());
//	    updatedCustomer.setName("Max");
//	    Address updatedAddress = new Address();
//	    updatedAddress.setCity("Berlin");
//	    updatedAddress.setStreet("Potsdamer Platz 1");
//	    updatedAddress.setZip(10785);
//	    updatedCustomer.setAddress(updatedAddress);
//	    shopService.updateCustomerById(updatedCustomer.getCustomerId(), updatedCustomer);
//
//	    Customer retrievedCustomer = shopService.getCustomerById(createdCustomer.getCustomerId());
//
//	    assertEquals("Max", retrievedCustomer.getName());
//	    assertEquals("Berlin", retrievedCustomer.getAddress().getCity());
//	    assertEquals("Potsdamer Platz 1", retrievedCustomer.getAddress().getStreet());  
//	}
//
//	@Test
//	void canAllCustomersBeFound() {
//		assertNotNull(shopService.getAllCustomers());
//	}
//
//	@Test
//	void canCustomerBeDeleted() throws Exception{
//		// Create a new customer
//		Customer customer = new Customer();
//		customer.setName("Freddy Krüger");
//		Address address = new Address();
//		address.setCity("Köln");
//		address.setStreet("Bahnhofstrasse 55");
//		address.setZip(50668);
//		customer.setAddress(address);
//		Customer createdCustomer = shopService.createCustomer(customer);
//
//		// Delete the customer
//		Integer customerId = createdCustomer.getCustomerId();
//		shopService.deleteCustomerById(customerId);
//	}
}
