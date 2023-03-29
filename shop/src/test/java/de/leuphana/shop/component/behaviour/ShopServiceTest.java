package de.leuphana.shop.component.behaviour;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopServiceTest{

//	@Autowired
//	ArticleRestConnectorRequester restConnector;
//
//	@Autowired
//	CustomerRestConnectorRequester customerRestConnector;
//	
//	@Autowired
//	ShopKafkaController shopKafkaController;
//
//	@Test
//	void canNewArticleBeInserted() {
//		Book book = new Book();
//		book.setArticleType("book");
//		book.setName("Sprechen Sie Mongo?");
//		book.setAuthor("Mr. Gold");
//		book.setManufactor("SnakeLand");
//		book.setPrice(3.40);
//		book.setBookCategory("Horror");
//		
//		CD cd = new CD();
//		cd.setArticleType("cd");
//		cd.setName("Fire Album");
//		cd.setArtist("Dj Fire");
//		cd.setManufactor("Dony");
//		cd.setPrice(4334.4);
//		assertNotNull(shopKafkaController.saveArticle(cd));
//		assertNotNull(shopKafkaController.saveArticle(book));
//	}
//
//	@Test
//	void canIgetAllArticles() {
//		assertNotNull(restConnector.getArticles());
//	}
//
//	@Test
//	void canBookBeFound() {
//		Article book = restConnector.getArticleById("book", "BK1");
//		assertNotNull(book);	
//	}
//
//	@Test
//	void canArticleBeUpdated() {
//		restConnector.getArticles();
//		canNewArticleBeInserted();
//		Book newBook = new Book();
//		newBook.setArticleId(1l);
//		newBook.setArticleType("book");
//		newBook.setName("Sprechen Sie Java 999");
//		newBook.setPrice(3.30);
//		shopKafkaController.updateArticle(newBook);
//	}
//	
//	
//	@Test 
//	void canBookDeleted(){
//		restConnector.getArticles();
//		assertNotNull(shopKafkaController.deleteArticle("book", "BK1"));
//	}

//	@Test
//	void canCustomerBeCreated() throws InterruptedException {
//		// Create a customer object
//	    Customer customer = new Customer();
//	    customer.setCustomerId(1);
//	    customer.setName("Max Mustermann");
//	    Address address = new Address();
//	    address.setAdressId(1);
//	    address.setCity("Lueneburg");
//	    address.setStreet("Soltauerstrasse 1");
//	    address.setZip(21335);
//	    customer.setAddress(address);
//	    
//	    customerProducer.sendCustomer(customer);
//
//	}

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
